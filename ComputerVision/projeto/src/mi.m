function I=mi(A,B) 
L=256;
A=double(A); 
B=double(B); 
     
na = hist(A(:),L); 
na = na/sum(na);

nb = hist(B(:),L); 
nb = nb/sum(nb);

n2 = hist2(A,B,L); 
n2 = n2/sum(n2(:));

I=sum(minf(n2,na'*nb)); 

function y=minf(pab,papb)

I=find(papb(:)>1e-12 & pab(:)>1e-12);
y=pab(I).*log2(pab(I)./papb(I));
