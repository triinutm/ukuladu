package view;

import hibernate.HibernateDBConnection;
import hibernate.ItemType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TreeView {

	HibernateDBConnection hDBC = new HibernateDBConnection();

	public String  drawTree() {
		
		String result ="";
		
		List<ItemType> mainItems = hDBC.getMainItems();
		for (ItemType item : mainItems) {
			System.out.println(item.getTypeName().toString());
			result+=item.getTypeName().toString() + "<br />";

			Set itemTypes = item.getItemTypes();
			List<ItemType> children = new ArrayList<ItemType>();
			Iterator it = itemTypes.iterator();

			while (it.hasNext()) {
				children.add((ItemType) it.next());
			}
			for (ItemType itemType : children) {
				System.out.println("-- " + itemType.getTypeName().toString());

				result+="-- " + itemType.getTypeName().toString() + "<br />";
				
				
				Set itemTypes1 = itemType.getItemTypes();
				List<ItemType> children1 = new ArrayList<ItemType>();
				Iterator it1 = itemTypes1.iterator();

				while (it1.hasNext()) {
					children1.add((ItemType) it1.next());
				}
				for (ItemType itemType1 : children1) {
					System.out.println("  --- "
							+ itemType1.getTypeName().toString());
					
					result+="  --- " + itemType1.getTypeName().toString() + "<br />";
				}

			}

		}
		return result;
	}

}
