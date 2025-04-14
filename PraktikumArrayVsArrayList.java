import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// Kelas untuk operasi Array
class ArrayOperations {
    // Method untuk menampilkan isi array
    public void traversal(int[] array) {
        System.out.println("Array Traversal: " + Arrays.toString(array));
    }

    // Method untuk pencarian linear pada array
    public int linearSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) return i; // jika ketemu, kembalikan index
        }
        return -1; // jika tidak ditemukan
    }

    // Method untuk pencarian binary pada array (syarat: array sudah terurut)
    public int binarySearch(int[] array, int value) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == value) return mid;
            if (array[mid] < value) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // Method untuk menyisipkan elemen ke posisi tertentu pada array
    public int[] insert(int[] array, int value, int position) {
        int[] newArray = new int[array.length + 1];
        // Salin elemen sebelum posisi
        System.arraycopy(array, 0, newArray, 0, position);
        newArray[position] = value; // Sisipkan elemen baru
        // Salin sisa elemen setelah posisi
        System.arraycopy(array, position, newArray, position + 1, array.length - position);
        return newArray;
    }

    // Method untuk menghapus elemen di posisi tertentu pada array
    public int[] delete(int[] array, int position) {
        int[] newArray = new int[array.length - 1];
        // Salin elemen sebelum posisi
        System.arraycopy(array, 0, newArray, 0, position);
        // Salin elemen setelah posisi
        System.arraycopy(array, position + 1, newArray, position, array.length - position - 1);
        return newArray;
    }
}

// Kelas untuk operasi ArrayList
class ArrayListOperations {
    // Method untuk menampilkan isi ArrayList
    public void traversal(ArrayList<Integer> list) {
        System.out.println("ArrayList Traversal: " + list);
    }

    // Method untuk menambahkan elemen ke ArrayList
    public void add(ArrayList<Integer> list, int value) {
        list.add(value);
    }

    // Method untuk menghapus elemen di posisi tertentu dari ArrayList
    public void remove(ArrayList<Integer> list, int index) {
        list.remove(index);
    }

    // Method untuk mencari elemen dalam ArrayList, mengembalikan index atau -1 jika tidak ketemu
    public int search(ArrayList<Integer> list, int value) {
        return list.indexOf(value);
    }

    // Method untuk mengurutkan isi ArrayList
    public void sort(ArrayList<Integer> list) {
        Collections.sort(list);
    }
}

// Kelas untuk membandingkan waktu eksekusi operasi pada Array vs ArrayList
class Comparison {
    // Method untuk membandingkan waktu pencarian linear di Array dan ArrayList
    public void compareSearch(int[] array, ArrayList<Integer> list, int value) {
        ArrayOperations arrayOps = new ArrayOperations();
        ArrayListOperations listOps = new ArrayListOperations();

        // Hitung waktu pencarian di Array
        long start = System.nanoTime();
        int arrayIndex = arrayOps.linearSearch(array, value);
        long end = System.nanoTime();
        double arrayTime = (end - start) / 1e6; // Konversi ke ms

        // Hitung waktu pencarian di ArrayList
        start = System.nanoTime();
        int listIndex = listOps.search(list, value);
        end = System.nanoTime();
        double listTime = (end - start) / 1e6;

        // Tampilkan hasil
        System.out.printf("Pencarian %d dalam Array: Ditemukan di indeks %d (%.4f ms)\n", value, arrayIndex, arrayTime);
        System.out.printf("Pencarian %d dalam ArrayList: Ditemukan di indeks %d (%.4f ms)\n", value, listIndex, listTime);
    }
}

// Kelas Main — tempat program dijalankan
public class PraktikumArrayVsArrayList {
    public static void main(String[] args) {
        // Inisialisasi object untuk masing-masing operasi
        ArrayOperations arrayOps = new ArrayOperations();
        ArrayListOperations listOps = new ArrayListOperations();
        Comparison comp = new Comparison();

        // Buat array dan ArrayList awal
        int[] array = {10, 20, 30, 40, 50};
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));

        // Traversal / Tampilkan isi
        arrayOps.traversal(array);
        listOps.traversal(list);
        System.out.println();

        // Pencarian nilai 30
        comp.compareSearch(array, list, 30);
        System.out.println();

        // Penyisipan nilai 25 di posisi ke-2
        array = arrayOps.insert(array, 25, 2);
        list.add(2, 25);
        System.out.println("Array setelah penyisipan elemen 25: " + Arrays.toString(array));
        System.out.println("ArrayList setelah penyisipan elemen 25: " + list);
        System.out.println();

        // Penghapusan elemen di indeks ke-3
        array = arrayOps.delete(array, 3);
        listOps.remove(list, 3);
        System.out.println("Array setelah penghapusan indeks 3: " + Arrays.toString(array));
        System.out.println("ArrayList setelah penghapusan indeks 3: " + list);
        System.out.println();

        // Pengurutan ArrayList
        listOps.sort(list);
        System.out.println("ArrayList setelah diurutkan: " + list);
    }
}
