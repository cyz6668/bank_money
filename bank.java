package bank;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.*;
import java.nio.*;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;
 

class account implements Serializable{
	public String name;
	public String accountnum;
	public String password;
	public double change;
	public Date t;
}
class consumer implements Serializable{
	account a=new account();
	public consumer() {
		
	}
	public consumer(String name,String num,String psd) {
		//a.password=new String(name);
		//a.accountnum=new String(num);
		//a.accountnum=new String(psd);
		a.change=0;
		a.password=psd;
		a.accountnum=num;
		a.name=name;
		//a.t=;
	}
	public void set(double money) {
		a.change=money;
	}
	
}




public class Bank {
	static String base64encode(String src) throws IOException {
        
        Encoder encoder = Base64.getEncoder();
        String result = encoder.encodeToString(src.getBytes());
         return result;

    }
	static String base64decode(String src)throws IOException{
		 Decoder decoder = Base64.getDecoder();
	     byte[] result = decoder.decode(src);
	     return new String(result);
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ObjectOutputStream oos=null;
		consumer con1=new consumer("赵","01",base64encode("123456"));
		consumer con2=new consumer("钱","02",base64encode("123457"));
		consumer con3=new consumer("孙","03",base64encode("123458"));
		consumer con4=new consumer("李","04",base64encode("123456"));
		List<consumer> lst=new LinkedList<consumer>();
		lst.add(con1);
		lst.add(con2);
		lst.add(con3);
		lst.add(con4);
		try {
			oos=new ObjectOutputStream(new FileOutputStream("G:\\con1.txt"));
			oos.writeObject(lst);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(oos!=null) {
				try {
					oos.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		ObjectInputStream ois=null;
		try {
			ois=new ObjectInputStream(new FileInputStream("G:\\con1.txt"));
			
			List<consumer>cons=(LinkedList<consumer>)ois.readObject();
			for(consumer con:cons) {
				System.out.println("account"+con.a.accountnum);
				System.out.println("name"+con.a.name);
				System.out.println("change"+con.a.change);
				System.out.println("code"+base64decode(con.a.password));
			}
		}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		finally {
			if(ois!=null) {
				try {
					ois.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}

}
