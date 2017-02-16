#!/bin/bash

# save the file as <git_directory>/.git/hooks/pre-commit

echo "Running Maven clean test for errors"
# retrieving current working directory
message_out='Build is '
CWD=`pwd`
MAIN_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
if [ $? -ne 0 ]; then
  echo 'Error while testing the code'
  message_out="$message_out Un"
  cd $CWD
  exit 1
else
	echo 'Success!'
fi
message_out="$message_out success"
# go back to current working dir
cd $CWD