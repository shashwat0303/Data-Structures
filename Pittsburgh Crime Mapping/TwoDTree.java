import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Shashwat Koranne
 * 
 * Following TwoDTree class is my own implementation of Two dimensional BST, which was
 * made to store the Crime class objects (the data of crimes committed in Pittsburgh in
 * 1990's.
 * 
 * Given a point on map, crimes committed in the radius of given distance can be mapped
 * on Google Maps.
 * 
 * Similarly, given a rectangular area patch, all the crimes committed during that time
 * in the given area can be mapped accordingly using method searchRectangle().
 */
public class TwoDTree {
	/**
	 * Root node to initialize the tree.
	 */
	Node root;
	
	/**
	 * Private class node to help save Crime objects in the tree.
	 */
	private class Node {
		/**
		 * Nodes left and right for navigational purposes within the tree.
		 */
		Node left, right;
		/**
		 * Crime object to be stored over a node.
		 */
		Crime crime;
		/**
		 * Attribute orientation to keep the track of node, if it's vertical
		 * or horizontal.
		 */
		boolean orientation;
		/**
		 * Node constructor to define nodes the way we want to.
		 * @param crime Crime data to be stored over the node
		 */
		public Node(Crime crime) {
			this.crime = crime;
		}
		
		/**
		 * Method to calculate the distance of the given crime location from another point.
		 * @param x X coordinate of the location on map
		 * @param y Y coordinate of the location on map
		 * @return distance between the given location and the crime scene
		 */
		public double distanceFrom(double x, double y) {
	    	double x1 = crime.getX();
	    	double y1 = crime.getY();
	    	
	    	double distanceSquared = Math.pow(x1 - x, 2) + Math.pow(y1 - y, 2);
	    	double distance = Math.sqrt(distanceSquared);
	    	return distance;
	    }
	}
	
	/**
	 * Constructor to initialized the tree by reading through the given CSV file.
	 * @param fileName Name of the CSV File
	 */
	public TwoDTree(String fileName) {
		root = null;
		String csvFile = fileName;
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
            	Crime crime = this.readLine(line);
            	this.insert(crime);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Tree has been initialzed with crimes of 1990's");
	}
	
	/**
	 * Helper method to break the comma separated data into an array.
	 * @param line Line in the CSV File
	 * @return array of crime object attributes
	 */
	private Crime readLine(String line) {
		String[] data = line.split(",");
		double x = Double.parseDouble(data[0]);
		double y = Double.parseDouble(data[1]);
		int time = Integer.parseInt(data[2]);
		String street = data[3];
		String offense = data[4];
		String date = data[5];
		int tract = Integer.parseInt(data[6]);
		double lat = Double.parseDouble(data[7]);
		double lon = Double.parseDouble(data[8]);
		Crime crime = new Crime(x, y);
		crime.setTime(time);
		crime.setStreet(street);
		crime.setOffense(offense);
		crime.setDate(date);
		crime.setTract(tract);
		crime.setLatLong(lat, lon);
		return crime;
	}
	
	/**
	 * Method to insert crime objects over the given node.
	 * @param crime Object to be added to the node element
	 */
	public void insert(Crime crime) {
		root = insert(root, crime);
	}
	
	/**
	 * private helper method to insert the Crime object.
	 * @param n Node of the tree
	 * @param crime Object to be added
	 * @return Node at which the object was addded
	 */
	private Node insert(Node n, Crime crime) {
		Node newNode = new Node(crime);
		if (n == null) {
			root = newNode;
			root.orientation = true;
			return root;
		}
		
		if (n.crime.equals(crime)) {
			return n;
		}
		
		if (n.orientation == true) {
			if (crime.getX() < n.crime.getX()) {
				if (n.left == null) {
					n.left = newNode;
					this.setLeftOrientation(n);
				} else {
					n.left = insert(n.left, crime);
				}
			} else {
				if (n.right == null) {
					n.right = newNode;
					this.setRightOrientation(n);
				} else {
					n.right = insert(n.right, crime);
				}
			}
		} else {
			if (crime.getY() < n.crime.getY()) {
				if (n.left == null) {
					n.left = newNode;
					this.setLeftOrientation(n);
				} else {
					n.left = insert(n.left, crime);
				}
			} else {
				if (n.right == null) {
					n.right = newNode;
					this.setRightOrientation(n);
				} else {
					n.right = insert(n.right, crime);
				}
			}
		}
		return n;
	}
	
	/**
	 * Private helper method to determine the orientation of a given node.
	 * @param crime Object whose node's orientation needs to be determined
	 * @return true if orientation is vertical
	 */
	private boolean level(Crime crime) {
		int counter = 1;
		if (root == null) {
			return false;
		}
		Node pointer = root;
		while (true) {
			if (pointer == null) {
				break;
			}
			if (counter % 2 == 1) {
				if (crime.getX() == pointer.crime.getX()) {
					break;
				} else if (crime.getX() > pointer.crime.getX()) {
					pointer = pointer.right;
				} else {
					pointer = pointer.left;
				}
				counter++;
			} else {
				if (crime.getY() == pointer.crime.getY()) {
					break;
				} else if (crime.getY() > pointer.crime.getY()) {
					pointer = pointer.right;
				} else {
					pointer = pointer.left;
				}
				counter++;
			}
		}
		if (counter % 2 == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Private helper method to set the orientation of the right child.
	 * @param parent Node whose children's node's orientation needed to be set.
	 */
	private void setRightOrientation(Node parent) {
		if (level(parent.crime) == true) {
			parent.right.orientation = false;
		} else {
			parent.right.orientation = true;
		}
	}
	
	/**
	 * Private helper method to set the orientation of the right child.
	 * @param parent Node whose children's node's orientation needed to be set.
	 */
	private void setLeftOrientation(Node parent) {
		if (level(parent.crime) == true) {
			parent.left.orientation = false;
		} else {
			parent.left.orientation = true;
		}
	}
	
	/**
	 * Method to print the elements in the tree.
	 */
	public void traverse() {
        traverse(root);
        System.out.println();
    }

	/**
	 * Private helper method to make recursive calls to traverse method.
	 * @param pointer Node that needs to be traversed from
	 */
    private void traverse(Node pointer) {
        if(pointer != null) {
            traverse(pointer.left);
            System.out.println(pointer.crime);
            traverse(pointer.right);
        }
    }
    
    /**
     * Method to obtain the list of crimes committed at a given location.
     * @param x X coordinate of the location on map
     * @param y Y coordinate of the location on map
     * @return list of crimes committed at a specified location
     */
    public List<Crime> search(double x, double y) {
    	List<Crime> crimes = new ArrayList<Crime>();
    	if (root == null) {
    		return null;
    	}
    	Node pointer = root;
    	while (true) {
    		if (pointer.crime.getX() == x && pointer.crime.getY() == y) {
    			crimes.add(pointer.crime);
    			while (pointer.crime.getX() == x && pointer.crime.getY() == y) {
    				pointer = pointer.right;
    				crimes.add(pointer.crime);
    			}
    			return crimes;
    		}
    		if (pointer.orientation == true) {
    			if (x < pointer.crime.getX()) {
    				if (pointer.left == null) {
    					return null;
    				} else {
    					pointer = pointer.left;
    				}
    			} else {
    				if (pointer.right == null) {
    					return null;
    				} else {
    					pointer = pointer.right;
    				}
    			}
    		} else {
    			if (y < pointer.crime.getY()) {
    				if (pointer.left == null) {
    					return null;
    				} else {
    					pointer = pointer.left;
    				}
    			} else {
    				if (pointer.right == null) {
    					return null;
    				} else {
    					pointer = pointer.right;
    				}
    			}
    		}
    	}
    }
    
    /**
     * Method to obtain the list of crimes committed within a radius from the given location.
     * @param x X coordinate of the location on map
	 * @param y Y coordinate of the location on map
     * @param range Distance form the location up to which nearest crimes need to be found
     * @return list of crimes committed in the area
     */
    public List<Crime> nearestCrimes(double x, double y, int range) {
    	List<Crime> nearestCrimes = new ArrayList<Crime>();
    	if (root == null) {
    		return null;
    	}
    	Node pointer = root;
    	while(true) {
    		double distance = pointer.distanceFrom(x, y);
			if (distance < range) {
				nearestCrimes.add(pointer.crime);
			}
    		if (pointer.orientation == true) {
    			if (x < pointer.crime.getX()) {
    				if (pointer.right == null) {
    					break;
    				}
    				pointer = pointer.left;
    			} else {
    				if (pointer.left == null) {
    					break;
    				}
    				pointer = pointer.right;
    			}
    		} else {
    			if (y < pointer.crime.getY()) {
    				if (pointer.right == null) {
    					break;
    				}
    				pointer = pointer.left;
    			} else {
    				if (pointer.left == null) {
    					break;
    				}
    				pointer = pointer.right;
    			}
    		}
    	}
    	return nearestCrimes;
    }

    /**
     * Method to obtain the list of crimes committed within a given rectangular area.
     * @param x1 X coordinate of the lower left corner of rectangular area on map
	 * @param y1 Y coordinate of the lower left corner of rectangular area on map
     * @param x2 X coordinate of the upper right corner of rectangular area on map
     * @param y1 Y coordinate of the upper right corner of rectangular area on map
     * @return list of crimes committed within a given area
     */
    public List<Crime> searchRectangle(double x1, double y1, double x2, double y2) {
    	List<Crime> crimes = new ArrayList<Crime>();
    	root = searchRectangle(root, crimes, x1, y1, x2, y2);
    	return crimes;
    }
    
    private Node searchRectangle(Node n, List<Crime> crimes, double x1, double y1, double x2, double y2) {
    	if (n == null) {
    		return null;
    	}
    	double x = n.crime.getX();
    	double y = n.crime.getY();
    	
    	if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
    		crimes.add(n.crime);
    	}
    	
    	if (n.orientation == true) {
    		if (x > x2) {
    			if (n.left == null) {
    				return n;
    			} else {
    				n.left = searchRectangle(n.left, crimes, x1, y1, x2, y2);
    			}
    		} else if (x < x1) {
    			if (n.right == null) {
    				return n;
    			} else {
    				n.right = searchRectangle(n.right, crimes, x1, y1, x2, y2);
    			}
    		} else {
    			n.left = searchRectangle(n.left, crimes, x1, y1, x2, y2);
    			n.right = searchRectangle(n.right, crimes, x1, y1, x2, y2);
    		}
    	} else {
    		if (y > y2) {
    			if (n.left == null) {
    				return n;
    			} else {
    				n.left = searchRectangle(n.left, crimes, x1, y1, x2, y2);
    			}
    		} else if (y < y1) {
    			if (n.right == null) {
    				return n;
    			} else {
    				n.right = searchRectangle(n.right, crimes, x1, y1, x2, y2);
    			}
    		} else {
    			n.left = searchRectangle(n.left, crimes, x1, y1, x2, y2);
    			n.right = searchRectangle(n.right, crimes, x1, y1, x2, y2);
    		}
    	}
    	return n;
    }

    /**
     * Method to generate .kml file to be uploaded to Google Maps for visualization purposes. 
     * @param crimes List of crimes to be visualized on Google Maps
     */
    public void toKML(List<Crime> crimes) {
    	BufferedWriter bw = null;
		FileWriter fw = null;
		
		StringBuilder doc = new StringBuilder("");
		doc.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
				+	"<kml xmlns=\"http://earth.google.com/kml/2.2\">"
				+	"<Document>"
				+	"  <Style id=\"style1\">"
				+	"  <IconStyle>"
				+	"    <Icon>"
				+	"      <href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue-dot.png</href>"
				+	"    </Icon>"
				+	"   </IconStyle>"
				+	"   </Style>");
		for (Crime crime : crimes) {
			doc.append("   <Placemark>");
			doc.append("<name>" + crime.offense +"</name>");
			doc.append("<description>" + crime.street + "</description>");
			doc.append("<styleUrl>#style1</styleUrl>"
					+	"<Point>");
			doc.append("<coordinates>" + crime.longitude + "," + crime.latitude + "," + 0.000000 + "</coordinates>");
			doc.append("      </Point>"
			+	    "</Placemark>");
		}

		doc.append("</Document>"
				+	"</kml>");
		
		try {
			fw = new FileWriter("crime.kml");
			bw = new BufferedWriter(fw);
			bw.write(doc.toString());
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
    }

	public static void main(String[] args) {
		TwoDTree tree = new TwoDTree("CrimeLatLonXY.csv");
		System.out.println("");
		List<Crime> nc = tree.nearestCrimes(1359951.000, 410726.000, 500);
		List<Crime> nearestCrimes = tree.searchRectangle(1357605.688, 411838.5393, 1358805.688, 413038.5393);
		for (Crime crime : nc) {
			System.out.println(crime.toString());
		}
		tree.toKML(nearestCrimes);
		
	}
}
