function [Seg Id Ncut] = NcutPartition(I, W, sNcut, sArea, id)

N = length(W);
d = sum(W, 2);
D = spdiags(d, 0, N, N);    % diagonal matrix
% Step 2 and 3. Solve generalized eigensystem (D -W)*S = S*D*U (12).
warning off;                % let me stop warning
[U,S] = eigs(D-W, D, 2, 'sm');
% 2nd smallest (1st smallest has all same value elements, and useless)
U2 = U(:, 2);
% Bipartition the graph at point that Ncut is minimized.
t = mean(U2);
t = fminsearch('NcutValue', t, [], U2, W, D);
A = find(U2 > t);
B = find(U2 <= t);
% Step 4. Decide if the current partition should be divided
% if either of partition is too small, stop recursion.
% if Ncut is larger than threshold, stop recursion.
ncut = NcutValue(t, U2, W, D);
if (length(A) < sArea || length(B) < sArea) || ncut > sNcut
    Seg{1}   = I;
    Id{1}   = id;          % for debugging
    Ncut{1} = ncut;        % for duebuggin
    return;
end
% Seg segments of A
[SegA IdA NcutA] = NcutPartition(I(A), W(A, A), sNcut, sArea, [id '-A']);
% Seg segments of B
[SegB IdB NcutB] = NcutPartition(I(B), W(B, B), sNcut, sArea, [id '-B']);
% concatenate cell arrays
Seg   = [SegA SegB];
Id   = [IdA IdB];
Ncut = [NcutA NcutB];
end
