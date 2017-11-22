clc;    % Clear the command window.
close all;  % Close all figures (except those of imtool.)
imtool close all;  % Close all imtool figures.
clear;  % Erase all existing variables.
workspace;  % Make sure the workspace panel is showing.

% Read a grayscale image
I = imread('./img/35008.jpg');

% Convert the RGB image to a grayscale image
GS = rgb2gray(I);

% Save the gray scale image
imwrite(GS,'./img/grayscale.png');

% Filtros
% Roberts
h1R = [-1 0; 0 1];
h2R = [0 -1; 1 0];

% Prewitt
h1P = [0 1 1; -1 0 1; -1 -1 0];
h2P = [-1 -1 0; -1 0 1; 0 1 1];

% Sobel
h1S = [0 1 2; -1 0 1; -2 -1 0];
h2S = [-2 -1 0; -1 0 1; 0 1 2];

% Laplaciano
hL = fspecial('laplacian',1);

% LoG
hLog = fspecial('log',[3 3],1);

I1R=imfilter(GS,h1R);
I2R=imfilter(GS,h2R);
IR = I1R + I2R;

I1P=imfilter(GS,h1P);
I2P=imfilter(GS,h1P);
IP = I1P + I2P;

I1S=imfilter(GS,h1S);
I2S=imfilter(GS,h2S);
IS = I1S + I2S;

IL=imfilter(GS,hL);
ILog=imfilter(GS,hLog);

% Save the binary image
imwrite(IR,'./img/roberts.png');

% Save the binary image
imwrite(IP,'./img/prewitt.png');

% Save the binary image
imwrite(IS,'./img/sobel.png');

% Save the binary image
imwrite(IL,'./img/laplacian.png');

% Save the binary image
imwrite(ILog,'./img/log.png');