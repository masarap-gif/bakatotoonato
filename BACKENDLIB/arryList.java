/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BACKENDLIB;
import DSA.NodeTransac;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ivan
 */
public class arryList {
  public static ArrayList<String> UserID = new ArrayList<>();
     public static ArrayList<String> Status = new ArrayList<>();
   public  static ArrayList<Integer> ISBN = new ArrayList<>();
   public static ArrayList<Date> DueDate = new ArrayList<>();
   public static ArrayList<Date> BrrwDate = new ArrayList<>();
   public  static ArrayList<String> TransacId = new ArrayList<>();
  public  static ArrayList<Double> Payment = new ArrayList<>();
  public static ArrayList<String> user = new ArrayList<>();
   
    
    
           
       public static String [] Header ={"Transaction ID", "ISBN", " UserID", "BorrowDate","DueDate","Amount", "Status"};
     public static String [] choices ={"Select Option", "Overdue","Paid"};
     public static String [] sotring ={"Select Option", "Weekly","Monthly", "Yearly"};
        
     
      private static ArrayList<NodeTransac> transactions = new ArrayList<>();
       public static ArrayList<NodeTransac> transactionsRecords = new ArrayList<>();
       
       
      
    public static void addTransaction(String transactionID, int code, String userId, Date borrowDate, Date dueDate, double amount, String status) {
    NodeTransac transaction = new NodeTransac(transactionID, code, userId, dueDate, borrowDate, amount, status);
    transactions.add(transaction);
       TransacId.add(transactionID);
}
//      public static void addTransaction(String transactionID, int code, String userId, Date borrowDate, Date dueDate) {
//    NodeTransac transaction = new NodeTransac(transactionID, code, userId, dueDate, borrowDate);
//    transactions.add(transaction);
//       TransacId.add(transactionID);
//}
           public static void addTransaction(String transactionID, int code, String userId, Date borrowDate, Date dueDate,String genre) {
    NodeTransac transaction = new NodeTransac(transactionID, code, userId, dueDate, borrowDate,genre);
    transactions.add(transaction);
       TransacId.add(transactionID);
}

public static void updateTransaction(int index, String status, double overdue) {
    if (index >= 0 && index < transactions.size()) {
        NodeTransac transaction = transactions.get(index);
        transaction.setstatus(status);
        transaction.setPrice(overdue);
    }
}
public static void updateTransaction(int index, NodeTransac updatedTransaction) {
    if (index >= 0 && index < transactions.size()) {
        transactions.set(index, updatedTransaction);
        System.out.println("Transaction updated successfully.");
    } else {
        System.out.println("Invalid index. No transaction updated.");
    }
}

public static void removeTransaction(int index) {
    if (index >= 0 && index < transactions.size()) {
        transactions.remove(index);
    } else {
        System.out.println("Invalid index: " + index);
    }
}

public static ArrayList<String> getTransactionIDs() {
    ArrayList<String> ids = new ArrayList<>();
    for (NodeTransac transaction : transactions) {
        ids.add(transaction.getID());
    }
    return ids;
}

public int findTransactionIndexById(String transacId) {
    for (int i = 0; i < transactions.size(); i++) {
        if (transactions.get(i).getID().equals(transacId)) {
            return i; // Returns the index of the matching transaction
        }
    }
    return -1; // Returns -1 if the transaction ID is not found
}

public static ArrayList<Integer> getISBNs() {
    ArrayList<Integer> isbns = new ArrayList<>();
    for (NodeTransac transaction : transactions) {
        isbns.add(transaction.code());
    }
    return isbns;
}
public void getUser(String user){
    if (user.equals("Teacher")){
        
    }
}
public int getTransactionCount() {
 
    return transactions.size();
}
   
//
//    public static ArrayList<NodeTransac> getTransactions() {
//        return transactions;
//    }

    public static ArrayList<NodeTransac> getTransactions() {
      return transactions;
    }
  public void quickSortTransactionsById() {
    quickSort(0, transactions.size() - 1); // Initial call to QuickSort
}

// QuickSort helper method
private void quickSort(int low, int high) {
    if (low < high) {
        // Find the pivot index
        int pivotIndex = partition(low, high);

        // Recursively sort the two halves
        quickSort(low, pivotIndex - 1); // Left half
        quickSort(pivotIndex + 1, high); // Right half
    }
}

// Partition method that rearranges the elements around the pivot
private int partition(int low, int high) {
    // Select the pivot (you can choose any element; here, we use the last element)
    String pivot = transactions.get(high).getID();
    
    // Pointer for the smaller element index
    int i = low - 1;

    // Loop through the list and rearrange based on pivot
    for (int j = low; j < high; j++) {
        // Compare the current transaction's ID with the pivot
        if (transactions.get(j).getID().compareTo(pivot) <= 0) {
            // Swap transactions[j] and transactions[i + 1]
            i++;
            swap(i, j);
        }
    }

    // Swap the pivot element with the element at i + 1
    swap(i + 1, high);

    // Return the pivot index after it has been placed in the correct position
    return i + 1;
}

// Swap two elements in the transactions list
private void swap(int i, int j) {
    NodeTransac temp = transactions.get(i);
    transactions.set(i, transactions.get(j));
    transactions.set(j, temp);
}
 

  public static void archiveTransaction(NodeTransac transaction) {
    transactionsRecords.add(transaction);
}
public void updateTransactionStatus(int index, String status, double fine, DefaultTableModel tb) {
    // Validate index and list size
    if (index < 0 || index >= transactions.size()) {
        System.out.println("Index out of bounds or transaction not found. Index: " + index);
        return;
    }

    // Get the transaction and update it
    NodeTransac transaction = transactions.get(index);
    transaction.setstatus(status);
    transaction.setPrice(fine);

    // Reflect updates in the table model if necessary
    if (tb != null && index < tb.getRowCount()) {
        tb.setValueAt(status, index, tb.findColumn("Status"));
        tb.setValueAt(fine, index, tb.findColumn("Amount"));
    } else {
        System.out.println("Table model update skipped: invalid index or missing table.");
    }
}

public static ArrayList<NodeTransac> getTransactionRecords() {
    return transactionsRecords;
}
    
    
}

