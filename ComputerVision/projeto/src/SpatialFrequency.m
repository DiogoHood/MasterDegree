function SF = SpatialFrequency(I)

GS = double(I); 
% double(rgb2gray(I));
[nRow,nCol] = size(GS); 
RF          = double(0.0);
CF          = double(0.0);
for i=1:nRow
    for j=2:nCol
        RF = RF + (GS(i,j) - GS(i,j-1))^2;
    end
end

for j=1:nCol
    for i=2:nRow
        CF = CF + (GS(i,j) - GS(i-1,j))^2;
    end
end

RF  = sqrt(RF/(nRow*nCol));
CF  = sqrt(CF/(nRow*nCol));
SF = sqrt(RF^2 + CF^2);

end

