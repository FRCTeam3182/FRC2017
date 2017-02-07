#!/bin/bash

#
# == fixAccounts.sh ==
#
# The team recently discovered that their git repositories were getting damaged
# for waht appeared to be no reason. We found today that it was really due to
# Google Drive's application syncing directories between computers. Let'sentinal
# avoid now and forever!
#
# Here's what you gotta do:
#
# 1. Commit and push to GitHub any changes you want, if necessary
# 2. Uninstall Google Drive
# 3. Run this script in git-bash like this:
#
#   cd ~/Google\ Drive
#   ./fixAccounts.sh
#
# And that's it!
#

# Start in the google drive directory
cd ~/Google\ Drive

# Delete all the desktop.ini files, everywhere, plz, ermahgerd
echo '# deleting desktop.ini files'
find -iname desktop.ini -delete

# Go into the google drive directory and delete everything not important
echo '# deleteing non-FRC 2017 files and directories'
cd ~/Google\ Drive
find -mindepth 1 -maxdepth 1 \
  -not -name "FRC 2017" \
  -not -name "fixAccounts.sh" #\
  #-delete

# And again the next directory deep
echo '# deleteing non-Programming files and directories'
cd FRC\ 2017
find -mindepth 1 -maxdepth 1 \
  -not -name "Programming" #\
  #-delete

# And again the next directory deep
echo '# deleteing non-Sources files and directories'
cd Programming
find -mindepth 1 -maxdepth 1 \
  -not -name "Sources" #\
  #-delete

# Finally go into the sources directory. This is where all the first name 
# directories are
cd Sources

# For each programmers directory...
for userDir in $(find -mindepth 1 -maxdepth 1 -type d); do
  echo "# working in user directory $userDir"
  
  # Go into that directory
  pushd "$userDir" > /dev/null
  
  # If this is Andrew's directory move his old FRC2017 down a level
  if [[ $userDir == 'Andrew' ]]; then
    mv FRC2017 FRC2017_
    mv FRC2017_/FRC2017 .
    rm -rf FRC2017_
  fi
  
  # For each directory there...
  for gitDir in */; do
  
    # Skip the broken directory
    if [[ "$gitDir" == *broken/ ]]; then
      echo 'skipping a broken directory directory'
      continue
    fi
    
    # Get rid of that last slash on the directory name we used in the for above
    gitDir="${gitDir::-1}"
  
    echo "# working in git directory $gitDir"
    
    # If there's a ".repaired" file, we're in the clear so skip this one
    if [[ -e "$gitDir/.repaired" ]]; then
      echo "skipping, already repaired"
      continue
    fi
    
    echo "repairing"
    
    # Otherwise, claim it's broken
    mv "$gitDir" "${gitDir}_broken"
    
    # And clone a new repository
    git clone https://github.com/FRCTeam3182/FRC2017.git
    if [[ $? != 0 ]]; then
      echo 'skipping new repo creation, since there already is one'
      continue
    fi
    
    # Put our sentinal file in there so we know not to muck with it later
    touch "$gitDir/.repaired"
    
    # Get the local user name and email from the old repo
    pushd "${gitDir}_broken" > /dev/null
    name=`git config user.name`
    echo "name  = $name"
    email=`git config user.email`
    echo "email = $email"
    popd > /dev/null
    
    # Set the user name and email
    pushd "$gitDir" > /dev/null
    git config --local user.name "$name"
    git config --local user.email "$email"
    git config --local -l
    popd > /dev/null
    
    # A little white space would be nice
    echo
    
    # And that's it! I think...
  done
  
  # A little more white space would be awesome
  echo 
  echo
  
  # Go back to the level above the programmer's directories
  popd > /dev/null
done

# Finally go back to the google drive
cd ~/Google\ Drive
