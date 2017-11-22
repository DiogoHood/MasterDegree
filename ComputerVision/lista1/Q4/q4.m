clc;    % Clear the command window.
close all;  % Close all figures (except those of imtool.)
imtool close all;  % Close all imtool figures.
clear;  % Erase all existing variables.
workspace;  % Make sure the workspace panel is showing.

% Read a grayscale image
I = imread('./img/predio.bmp');

% Convert the RGB image to a grayscale image
GS = rgb2gray(I);

% Find the edges in the image using the edge function
BW = edge(GS,'canny');

% % Save the blurred image
imwrite(BW,'./img/canny.png');
% 
% Compute the Hough transform of the binary image returned by edge
[H,theta,rho] = hough(BW);

% Find the peaks in the Hough transform matrix, H, using the houghpeaks function.
P  = houghpeaks(H,10,'threshold',ceil(0.7*max(H(:))));

ceil(max(H(:)))

% Find lines in the image using the houghlines function.
lines = houghlines(BW,theta, rho, P,'FillGap',50);

% Create a plot that displays the original image with the lines superimposed on it.
figure, imshow(GS), hold on
max_len = 0;
for k = 1:length(lines)
    
   xy = [lines(k).point1; lines(k).point2];
   plot(xy(:,1),xy(:,2),'LineWidth',2,'Color','green');

   % Plot beginnings and ends of lines
   plot(xy(1,1),xy(1,2),'x','LineWidth',2,'Color','yellow');
   plot(xy(2,1),xy(2,2),'x','LineWidth',2,'Color','red');

end

[a,floors] = size(lines);

result = ['Existem ', num2str(floors), ' andares.',];

disp(result);