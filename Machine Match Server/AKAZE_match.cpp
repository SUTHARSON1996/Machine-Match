#include <opencv2/features2d.hpp>
#include <opencv2/imgcodecs.hpp>
#include <opencv2/opencv.hpp>
#include <vector>
#include <iostream>
#include <string>

using namespace std;
using namespace cv;

const float nn_match_ratio = 0.8f;   // Nearest neighbor matching ratio

int main(int argc, char** argv)
{
		
	string imageName("");
	imageName = argv[1];

    Mat img1 = imread(imageName.c_str(), IMREAD_GRAYSCALE);
    Mat img2 = imread("test.jpg", IMREAD_GRAYSCALE);
	
	resize(img1,img1,cv::Size(500,500));
	resize(img2,img2,cv::Size(500,500));

    Mat homography;
    FileStorage fs("../data/H1to3p.xml", FileStorage::READ);
    fs.getFirstTopLevelNode() >> homography;

    vector<KeyPoint> kpts1, kpts2;
    Mat desc1, desc2;

    Ptr<AKAZE> akaze = AKAZE::create();
    akaze->detectAndCompute(img1, noArray(), kpts1, desc1);
    akaze->detectAndCompute(img2, noArray(), kpts2, desc2);

    BFMatcher matcher(NORM_HAMMING);
    vector< vector<DMatch> > nn_matches;
    matcher.knnMatch(desc1, desc2, nn_matches, 2);

    vector<KeyPoint> matched1, matched2;
    vector<DMatch> good_matches;
    for(size_t i = 0; i < nn_matches.size(); i++) {
        DMatch first = nn_matches[i][0];
        float dist1 = nn_matches[i][0].distance;
        float dist2 = nn_matches[i][1].distance;

        if(dist1 < nn_match_ratio * dist2) {
            matched1.push_back(kpts1[first.queryIdx]);
            matched2.push_back(kpts2[first.trainIdx]);
        }
    }

    

    
    
    //cout << "A-KAZE Matching Results" << endl;
    //cout << "*******************************" << endl;
    //cout << "# Keypoints 1:                        \t" << kpts1.size() << endl;
    //cout << "# Keypoints 2:                        \t" << kpts2.size() << endl;
    cout << matched1.size();	//no of matches
    

    return 0;
}
