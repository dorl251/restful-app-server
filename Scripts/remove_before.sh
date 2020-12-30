#! /bin/bash
find /opt/codedeploy-agent/deployment-root/68a9eade-af15-48ac-92a9-35d36d453e73/* -maxdepth 0 -type 'd' | grep -v
$(stat -c '%Y:%n' /opt/codedeploy-agent/deployment-root/68a9eade-af15-48ac-92a9-35d36d453e73/* | sort -t: -n | tail -1 | cut -d: -f2- | cut -c 3-) | xargs rm -rf
sudo rm -rf /home/ec2-user/*