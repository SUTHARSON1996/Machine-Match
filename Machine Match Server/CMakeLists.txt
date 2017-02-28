cmake_minimum_required(VERSION 2.8)
project( feat )
find_package( OpenCV REQUIRED )
add_executable( feat AKAZE_match.cpp )
target_link_libraries( feat ${OpenCV_LIBS} )

