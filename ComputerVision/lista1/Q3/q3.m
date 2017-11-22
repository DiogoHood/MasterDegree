clc;    % Clear the command window.
close all;  % Close all figures (except those of imtool.)
imtool close all;  % Close all imtool figures.
clear;  % Erase all existing variables.
workspace;  % Make sure the workspace panel is showing.

% Read a grayscale image
% I = imread('./img/69020.jpg');
% I = imread('./img/42049.jpg');
I = imread('./img/113044.jpg');

hsv = rgb2hsv(I);
h = hsv(:,:,1);
s = hsv(:,:,2);
v = hsv(:,:,3);
imwrite(h,'./img/h.png');
imwrite(s,'./img/s.png');
imwrite(v,'./img/v.png');

% Filter the image with a Gaussian filter with standard deviation of 30
Iblur = imgaussfilt(I, 35);

% Save the blurred image
imwrite(Iblur,'./img/blurred.png');

% Convert the RGB image to a hsv image
hsv_Iblur = rgb2hsv(Iblur);

% Get a H channel of the hsv image
h_Iblur = hsv_Iblur(:,:,1);

% Save the channel H blurred
imwrite(h_Iblur,'./img/channelHblurred.png');

% Calculate a threshold [0,1]
level = graythresh(h_Iblur);

% Convert the RGB image to a hsv image
hsv = rgb2hsv(I);

% Get a H channel of the hsv image
h = hsv(:,:,1);

% Segment the image according to threshold
BW = (h >= level);

% Save the channel H blurred
imwrite(BW,'./img/binaryanimal.png');

% imshow(BW);

% Create the Complement of the binary image
BW2 = imcomplement(BW);

% Fill Holes in a Binary Image
BW3 = imfill(BW2,'holes');

% Create the Complement of the binary image
BW4 = imcomplement(BW3);

% SE = strel('disk', 3);
% BW5 = imclose(BW4,SE);

% Save the channel H blurred
imwrite(BW4,'./img/resultanimal.png');
imshow(BW4);