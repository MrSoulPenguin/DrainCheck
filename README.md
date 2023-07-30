# DrainCheck
An attempt at scanning minecraft mod jars to see if they may be impacted by the BleedingPipe vulnerability.

Should be used as a way to see where to start looking for vulnerabilities. **Should not be used as a set in stone check**.

Also, I know the vulnerablity only impacts ``ObjectInputStreams``. But just to be safe, this also checks for ``ObjectOutputStreams`` aswell.

## Usage
Download the latest DrainCheck jar from the [releases](https://github.com/MrSoulPenguin/DrainCheck/releases). Along with run_scan.bat or run_scan.sh
depending on if you are running Windows or Linux respectively.

Put the downloaded DrainCheck jar and script in the same directory.

Have all of the jars you want to scan in one directory. It doesn't have to be the same as the DrainCheck jar and script.

Then run the script with the path to the directory containing the jars as an arguement. Like the following.

``run_scan.bat C:\Users\bob\Desktop\jars``
