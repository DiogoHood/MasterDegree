clc;    % Clear the command window.
close all;  % Close all figures (except those of imtool.)
imtool close all;  % Close all imtool figures.
clear;  % Erase all existing variables.
workspace;  % Make sure the workspace panel is showing.

% Read a grayscale image
A = imread('./img/objetos.bmp');

% Calculate a threshold [0,1]
level = graythresh(A);

% Convert the image into a binary image
BW = imbinarize(A,level);

% Save the binary image
imwrite(BW,'./img/binary.png');

% Create the Complement of the binary image
BW2 = imcomplement(BW);

% Save the binary image complement
imwrite(BW2,'./img/binarycomplement.png');

% Fill Holes in an opening morphological operation
se = strel('disk',5);
BW3 = imopen(BW2,se);

% Save the binary image complement filled
imwrite(BW3,'./img/binaryfilled.png');

% Find the connected components in the array
CC = bwconncomp(BW3)

% Get number of pixels of each connected component
numOfPixels = cellfun(@numel,CC.PixelIdxList)

% Calculate the mean
M = mean(numOfPixels)

% If the number of pixels is greater than the average is screw
count = numOfPixels >= M;

nnz(~count)
nnz(count)