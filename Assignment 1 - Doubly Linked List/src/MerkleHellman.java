import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Shashwat Koranne
 * 
 * The following method employs Merkle - Hellman Knapsack Cryptosystem
 * to encrypt and decrypt characters.
 */
public class MerkleHellman {
	/**
	 * Prime number larger than the sum of super increasing series.
	 */
	BigInteger Q;
	/**
	 * Coprime of Q in the range [1, Q).
	 */
	BigInteger r;
	/**
	 * List to hold public keys.
	 */
	List<BigInteger> listOne = new LinkedList<BigInteger>();
	/**
	 * List to hold private keys.
	 */
	List<BigInteger> listTwo;
	
	/**
	 * Constructor that initialized the super increasing series for encryption.
	 */
	public MerkleHellman() {
		listOne.add(BigInteger.valueOf(2));
		listOne.add(BigInteger.valueOf(7));
		listOne.add(BigInteger.valueOf(11));
		listOne.add(BigInteger.valueOf(21));
		listOne.add(BigInteger.valueOf(42));
		listOne.add(BigInteger.valueOf(89));
		listOne.add(BigInteger.valueOf(180));
		listOne.add(BigInteger.valueOf(354));
	}
	
	/**
	 * Methods returns the encrypted BigInteger object that encodes a character.
	 * @param num is the integer equivalent of the character as per ASCII table
	 * @return BigInteger object that represents the character
	 */
	public BigInteger encrypt(int num) {
		listTwo = new LinkedList<BigInteger>();
		BigInteger q = BigInteger.valueOf(0);
		BigInteger encrypted = BigInteger.valueOf(0);
		int length = listOne.size();
		for (int i = 0; i < length; i++) {
			q = q.add(listOne.get(i));
		}
		Q = BigInteger.valueOf(881);
		r = BigInteger.valueOf(588);
		for (int i = 0; i < length; i++) {
			BigInteger toAdd = listOne.get(i).multiply(r).mod(Q);
			listTwo.add(toAdd);
		}
		
		int binary = this.convertDtoB(num);
		
		for (int j = length - 1; j >= 0; j--) {
			if (j == 0) {
				continue;
			}
			int binaryUnit = binary % 10;
			binary = binary / 10;
			encrypted = encrypted.add(BigInteger.valueOf(binaryUnit).multiply(listTwo.get(j)));
		}
		return encrypted;
	}
	
	/**
	 * Methods converts the entire string to a BigInteger object.
	 * @param str that needs to be encrypted
	 * @return BigInteger object equivalent of the string
	 */
	public BigInteger encryptMessage(String str) {
		int length = str.length();
		BigInteger finalCode = BigInteger.valueOf(0);
		for (int i = 0; i < length; i++) {
			long a = (long) (1L * Math.pow(10, 7*i));
			BigInteger power = BigInteger.valueOf(a);
			BigInteger code = this.encrypt((int) str.charAt(i));
			finalCode = finalCode.multiply(power).add(code);
		}
		return finalCode;
	}
	
	/**
	 * Methods converts the encrypted BigInteger object to a String.
	 * @param num that needs to be decrypted
	 * @return String that is decrypted from the given BigInteger object
	 */
	public String decryptMessage(BigInteger num) {
		ArrayList<Character> characters = new ArrayList<Character>();
		StringBuilder str = new StringBuilder("");
		while(num.compareTo(BigInteger.valueOf(0)) == 1) {
			BigInteger big = num.mod(BigInteger.valueOf((long) (Math.pow(10, 7))));
			char toAdd = this.decrypt(big);
			characters.add(toAdd);
			num = num.divide(BigInteger.valueOf((long) (Math.pow(10, 7))));
		}
		for (int i = characters.size() - 1; i >= 0; i++) {
			str.append(characters.get(i).toString());
		}
		return str.toString();
	}
	
	/**
	 * Method to convert a decimal to binary
	 * @param num decimal to be converted to binary
	 * @return binary representation of an integer
	 */
	public int convertDtoB(int num) {
		int binary = 0;
		List<Integer> list = new ArrayList<Integer>();
		while (num > 0) {
			list.add(num % 2);
			num = num / 2;
		}
		
		for (int i = list.size() - 1; i >= 0; i--) {
			binary = (int) (binary + list.get(i) * Math.pow(10, i));
		}
		
		return binary;
	}
	
	/**
	 * Method to convert a binary to decimal
	 * @param binary binary to be converted to decimal
	 * @return decimal representation of a binary number
	 */
	public int convertBtoD(int binary) {
		int temp = binary;
		int decimal = 0;
		int length = 0;
		while (temp != 0) {
			temp = temp / 10;
			length++;
		}
		for (int i = 0; i < length; i++) {
			decimal = (int) (decimal + (binary % 10 * Math.pow(2, i)));
			binary = binary / 10;
		}
		return decimal;
	}
	
	/**
	 * Method to return the character hidden in the given encrypted BigInteger object
	 * @param num BigInteger object that needs to be decrypted
	 * @return character that is hidden in the encrytped BigInteger object
	 */
	public char decrypt(BigInteger num) {
		int binary = 0;
		BigInteger rInv = r.modInverse(Q);
		BigInteger sum = num.multiply(rInv).mod(Q);
		while(sum.compareTo(new BigInteger("0")) == 1) {
			for (int i = 0; i < listOne.size(); i++) {
				if (i == listOne.size() - 1) {
					if (listOne.get(i).intValue() < sum.intValue()) {
						sum = sum.subtract(listOne.get(i));
						binary = (int) (binary + Math.pow(10, 7-i));
					} else {
						continue;
					}
				} else {
					if (listOne.get(i).intValue() == sum.intValue()) {
						sum = sum.subtract(listOne.get(i));
						binary = (int) (binary + Math.pow(10, 7-i));
					} else if (listOne.get(i).intValue() < sum.intValue()) {
						if (listOne.get(i + 1).intValue() > sum.intValue()) {
							sum = sum.subtract(listOne.get(i));
							binary = (int) (binary + Math.pow(10, 7-i));
						} else {
							continue;
						}
					}
				}
			}
		}
		int charEquivalent = this.convertBtoD(binary);
		char result = (char) charEquivalent;
		return result;
	}

	public static void main(String[] args) {
		MerkleHellman mh = new MerkleHellman();
		String name = "Welcome to Data Structures and Algorithms";
		System.out.println(mh.encrypt((int) '#'));
		System.out.println(mh.decrypt(mh.encrypt((int) '#')));
	}
}
