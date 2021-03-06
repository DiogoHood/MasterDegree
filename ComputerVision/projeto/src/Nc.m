function [SegI Knc] = Nc(I,SI,SX,r,sNcut,sArea)
%% ncutImageSegment
[nRow, nCol,c] = size(I);
N = nRow * nCol;
V = reshape(I, N, c);                      
%% ncutComputeW                            
W = sparse(N,N);                         
F = reshape(I, N, 1, c);                  
X = cat(3, repmat((1:nRow)', 1, nCol), repmat((1:nCol), nRow, 1));
X = reshape(X, N, 1, 2);                  

for ic=1:nCol                              
    for ir=1:nRow                      
        fprintf('%d/%d\n',ic,nCol);
        
        jc = (ic - floor(r)) : (ic + floor(r));
        jr = ((ir - floor(r)) :(ir + floor(r)))';
        jc = jc(jc >= 1 & jc <= nCol);
        jr = jr(jr >= 1 & jr <= nRow);
        jN = length(jc) * length(jr);

        i = ir + (ic - 1) * nRow;
        j = repmat(jr, 1, length(jc)) + repmat((jc -1) * nRow, length(jr), 1);
        j = reshape(j, length(jc) * length(jr), 1);


        XJ = X(j, 1, :);
        XI = repmat(X(i, 1, :), length(j), 1);
        DX = XI - XJ;
        DX = sum(DX .* DX, 3);

        constraint = find(sqrt(DX) <= r);
        j = j(constraint);
        DX = DX(constraint);

        FJ = F(j, 1, :);
        FI = repmat(F(i, 1, :), length(j), 1);
        DF = FI - FJ;
        DF = sum(DF .* DF, 3);
        W(i, j) = exp(-DF / (SI*SI)) .* exp(-DX / (SX*SX));
    end
end

%% ncutPartition
Seg = (1:N)';                             
id = 'ROOT';                              
% Compute D
N = length(W);
d = sum(W, 2);
D = spdiags(d, 0, N, N);
% Step 2 and 3. Solve generalized eigensystem (D -W)*S = S*D*U (12).
warning off; % let me stop warning
[U,S] = eigs(D-W, D, 2, 'sm');
% 2nd smallest (1st smallest has all same value elements, and useless)
U2 = U(:, 2);
% Bipartition the graph at point that Ncut is minimized.
t = mean(U2);
t = fminsearch('NcutValue', t, [], U2, W, D);
A = find(U2 > t);
B = find(U2 <= t);
% Step 4. Decide if the current partition should be divided
x = (U2 > t);
x = (2 * x) - 1;
d = diag(D);
k = sum(d(x > 0)) / sum(d);
b = k / (1 - k);
y = (1 + x) - b * (1 - x);
ncut = (y' * (D - W) * y) / ( y' * D * y );
%% itteration
if ((length(A) < sArea || length(B) < sArea) || ncut > sNcut)
    Seg{1}   = Seg;
    return;
end
% Seg segments of A
[SegA IdA NcutA] = NcutPartition(Seg(A), W(A, A), sNcut, sArea, [id '-A']);
% Seg segments of B
[SegB IdB NcutB] = NcutPartition(Seg(B), W(B, B), sNcut, sArea, [id '-B']);
% concatenate cell arrays
Seg  = [SegA SegB];
Id   = [IdA IdB];
Ncut = [NcutA NcutB];

Inc     = zeros(size(I),'double');
IncAux  = cell(length(Seg),1);
SegI    = cell(length(Seg),1);
for k=1:length(Seg)
 [r, c] = ind2sub(size(I),Seg{k});
 for i=1:length(r)
 Inc(r(i),c(i),1:1) = uint8(round(mean(V(Seg{k}, :))));
 end
 IncAux{k}  = Inc;
 cellContents = uint8(IncAux{k});
 SegI{k}      = cellContents;
 Inc  = zeros(size(I),'double');
end

Knc = length(Seg);

end