import java.util.*;
import java.io.*;
class MySort implements Comparator<Tuple>{
	public int compare(Tuple t1,Tuple t2){
		return t1.score-t2.score;
	}
}
class Tuple {
	int index;
	int score;
	Tuple(int i, int s) {
		index = i;
		score = s;
	}
}
class Library{
    int numbooks;
	int signup;
	int booksperday;
	Tuple books[];

	Library(int a, int b,int c,Tuple d[]){
        numbooks = a;
		signup=b;
		booksperday=c;
		books=new Tuple[a];
		for(int i=0;i<a;i++){
			books[i]=new Tuple(d[i].index,d[i].score);
		}
		Arrays.sort(books,new MySort());
	//	System.out.println("hi");

	//	for(Tuple i : books)
	//		System.out.println(i.index+" "+i.score);
	}
}

class Test{
  public static void main(String args[]){

    StringBuilder sb = new StringBuilder();
    try {
        BufferedReader br = new BufferedReader(new FileReader("b_read_on.txt"));
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }
    } catch (Exception e) {

    }

    String[] arr = sb.toString().split("\n");
    int tot_book = Integer.parseInt(arr[0].split(" ")[0]);
    int tot_lib = Integer.parseInt(arr[0].split(" ")[1]);
    int deadline = Integer.parseInt(arr[0].split(" ")[2]);
    //System.out.println(tot_book + "\t" + tot_lib + "\t" + deadline);
    int[] scores = new int[tot_book];
    //HashMap<Integer,Integer> h = new HashMap<>();
    for(int i = 0; i < scores.length; i ++) {
        scores[i] = Integer.parseInt(arr[1].split(" ")[i]);
        //h.put(i,scores[i]);
    }
    /*for(int i = 0; i < scores.length; i ++) {
        System.out.print(scores[i] + "\t");
    }*/
    int temp = 2;
    Library[] libraries = new Library[tot_lib];
    for(int i = 0; i < tot_lib; i++) {
        String one = arr[temp++];
        String two = arr[temp++];
        int temp_nob = Integer.parseInt(one.split(" ")[0]);
        int temp_signup = Integer.parseInt(one.split(" ")[1]);
        int temp_booksperday = Integer.parseInt(one.split(" ")[2]);
        Tuple[] tempArr = new Tuple[temp_nob];
        for(int j = 0; j < temp_nob; j ++) {
            tempArr[j] = new Tuple(Integer.parseInt(two.split(" ")[j]), scores[Integer.parseInt(two.split(" ")[j])]);
        }
        libraries[i] = new Library(temp_nob, temp_signup, temp_booksperday, tempArr);
    }
    computeCost(libraries,deadline);

  }

  static HashSet<Integer> set=new HashSet<>();
  static int gc=0;

  static void computeCost(Library arr[],int deadline){

		ArrayList<ArrayList<Integer>> aList = new ArrayList<>();
		int i=0;
		for(i=0;i<arr.length;i++){
			gc+=arr[i].signup;
			int days = gc;
			int count = 0;
			ArrayList<Integer> aj = new ArrayList<>();
			aj.add(i);
			ArrayList<Integer> ak = new ArrayList<>();
			int j = arr[i].numbooks-1;
			while(j>=0){
					int k = 0;
					while(k<arr[i].booksperday && j>=0){
					//	System.out.println(j);
						if(j>=0 && !set.contains(arr[i].books[j].index)){
							count++;
							ak.add(arr[i].books[j].index);
							set.add(arr[i].books[j].index);
							k++;
							j--;
						}
						else 
							j--;
						days++;
					}
					if(days > deadline)
						break;
			}
			aj.add(count);
			aList.add(aj);
			aList.add(ak);
			if(gc > deadline)
				break;
		}
	//	StringBuilder sb = new StringBuilder(i);
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter("abc.txt"));
			writer.write(String.valueOf(i)+"\n");
			for(int l=0;l<aList.size();l++){
			for(int g=0;g<aList.get(l).size();g++){
				writer.write(aList.get(l).get(g)+" ");
			}
			writer.write("\n");
		}
		writer.close();
	}
		catch(Exception E){}
	}
}


class processLibrary{
		
}

