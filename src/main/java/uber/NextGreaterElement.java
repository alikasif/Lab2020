package uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NextGreaterElement {

            public List<Integer> nextLargerElement(List<Integer> arr, int n)
            {
                // Initialize a list to store the next greater
                // elements.
                List<Integer> v = new ArrayList<>(Collections.nCopies(n, -1));

                // Initialize a variable to keep track of the
                // maximum element seen so far.
                long mx = arr.get(n - 1);

                // Iterate over the array from right to left.
                for (int i = n - 2; i >= 0; i--) {
                    // If the element to the right is greater than
                    // the current element, then the next greater
                    // element for the current element is the
                    // element to the right.
                    if (arr.get(i + 1) > arr.get(i)) {
                        v.set(i, arr.get(i + 1));
                    }
                    else {
                        // If the next greater element for the
                        // element to the right is greater than the
                        // current element, then the next greater
                        // element for the current element is the
                        // next greater element for the element to
                        // the right.
                        if (v.get(i + 1) > arr.get(i)) {
                            v.set(i, v.get(i + 1));
                        }
                        else if (mx > arr.get(i)) {
                            // If there is no next greater element
                            // to the right that is greater than the
                            // current element, then we need to find
                            // the next greater element that is
                            // greater than the current element and
                            // to its right. Initialize a variable
                            // to keep track of the index of the
                            // next greater element.
                            int k = i + 1;
                            // Iterate over the array from the
                            // current element to its right to find
                            // the next greater element.
                            while (arr.get(k) <= arr.get(i)) {
                                k++;
                            }
                            v.set(i, arr.get(k));
                        }
                        else {
                            // If there is no next greater element
                            // to the right that is greater than the
                            // current element and there is no next
                            // greater element to the right that is
                            // greater than the next greater element
                            // for the current element, then there
                            // is no next greater element for the
                            // current element.
                            v.set(i, -1);
                        }
                    }
                    // Update the maximum element seen so far.
                    mx = Math.max(arr.get(i), mx);
                }
                // Return the list containing the next greater
                // elements.
                return v;
            }
           public static void main(String[] args)
            {
                // Input array
                List<Integer> arr
                        = Arrays.asList(3, 8, 4, 1, 2, 6, 7, 2);
                int n = arr.size();
                // Create an instance of the Solution class
                NextGreaterElement sol = new NextGreaterElement();
                // Call the nextLargerElement function
                List<Integer> result = sol.nextLargerElement(arr, n);
                // Print the result
                System.out.println(arr);
                System.out.println(result);
                System.out.println();
            }
    }


