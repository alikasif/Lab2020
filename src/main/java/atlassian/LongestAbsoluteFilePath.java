package atlassian;

import java.util.Arrays;

public class LongestAbsoluteFilePath {
    public static void main(String[] args) {

        String path = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";

        String[] split = path.split("\n");
        System.out.println(split.length);

        int c = 0;
        int level =-1;
        int prevlevel =-2;

        for(String s : split) {
            String[] split1 = s.split("\t");
            int i = s.lastIndexOf("\t");
            System.out.println(Arrays.toString(split1));
            System.out.println(i);
            prevlevel = level;
            level = level + i+1;
        }
        //System.out.println(Arrays.toString(split));
    }
}