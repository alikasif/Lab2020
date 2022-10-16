    package google2022;

    import scala.Char;

    public class LongestCommonPrefix {

        public static void main(String[] args) {

            String[] words = {"flower", "flowht", "flow"};

            String prefix = "";

            int s = -1;
            int e = -1;

            int i = 0;
            int j = 0;
            boolean run = true;
            while(true) {

                for( String w1 : words) {
                    if (i >= w1.length()) {
                        run = false;
                    }
                }
                if( !run)
                    break;

                    if( s == -1) {
                        s = i;
                        e=-1;
                    }
                    Character c = null;
                    for(String w : words) {
                        if ( c == null)
                            c = w.charAt(i);
                        else if (w.charAt(i) == c) {
                            continue;
                        }
                        else {
                            System.out.println("longest prefix so far " + s +" "+e);
                            s = -1;
                            break;
                        }
                    }
                    if( s != -1) {
                        e = i;
                    }

                i++;
            }
            System.out.println( s+  " "+ e);
        }
    }