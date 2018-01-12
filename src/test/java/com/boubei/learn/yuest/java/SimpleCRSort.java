package com.boubei.learn.yuest.java;

/**
*/
public class SimpleCRSort {
   public static void insertionSort(final int[] array) {
       if (array == null || array.length == 0) {
           throw new IllegalArgumentException("array");
       }
       
       int length = array.length;
       for (int outLoop = 1; outLoop < length; outLoop++) {
           int temp = array[outLoop];
           for (int innerLoop = outLoop - 1; innerLoop >= 0; innerLoop--) {
               if (array[innerLoop] > temp) {
                   array[innerLoop + 1] = array[innerLoop];
                   if (innerLoop == 0) {
                       array[innerLoop] = temp;
                   }
               } else {
                   array[innerLoop + 1] = temp;
                   break;
               }
           }
       }
   }
   
   public static void insertionSort1(final int[] array) {
       if (array == null || array.length == 0) {
           throw new IllegalArgumentException("array");
       }
       
       int length = array.length;
       int temp;
       int innerLoop;
       for (int outLoop = 1; outLoop < length; outLoop++) {
           temp = array[outLoop];
           innerLoop = outLoop;
           while (innerLoop > 0 && array[innerLoop - 1] >= temp) {
               array[innerLoop] = array[innerLoop-1];
               --innerLoop;
           }
           array[innerLoop] = temp;
       }
   }
    
   private static void swap(final int[] array, final int left, final int right) {
       int temp = array[left];
       array[left] = array[right];
       array[right] = temp;
   }
}