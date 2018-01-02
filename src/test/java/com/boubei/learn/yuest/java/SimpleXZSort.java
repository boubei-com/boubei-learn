package com.boubei.learn.yuest.java;

/**
*
*
* @author beanlam
* @date 2016年3月9日 下午11:26:20
* @version 1.0
*
*/
public class SimpleXZSort {

   public static void bubbleSort(final int[] array) {
       if (array == null || array.length == 0) {
           throw new IllegalArgumentException("array");
       }
       
       int length = array.length;
       for (int outLoop = length - 1; outLoop > 0; outLoop-- ) {
           for (int innerLoop = 0; innerLoop < outLoop; innerLoop++) {
               if (array[innerLoop] > array[innerLoop + 1]) {
                   swap(array, innerLoop, innerLoop + 1);
               }
           }
       }
   }
   
   private static void swap(final int[] array, final int left, final int right) {
       int temp = array[left];
       array[left] = array[right];
       array[right] = temp;
   }
}
