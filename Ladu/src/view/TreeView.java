package view;

import hibernate.HibernateDBConnection;
import hibernate.ItemType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TreeView {
	
	public TreeView() {
		
	}

	HibernateDBConnection hDBC = new HibernateDBConnection();

	public String  drawTree(long cat) {
		
		String result ="";
		
		List<ItemType> mainItems = hDBC.getMainItems();
		for (ItemType item : mainItems) {
			System.out.println(item.getTypeName().toString());
			result += getLink(item, cat);
//			result+="<a href='" + item.getItemType() + "' >" + item.getTypeName().toString() + "</a><br />";
			Set itemTypes = item.getItemTypes();
			List<ItemType> children = new ArrayList<ItemType>();
			Iterator it = itemTypes.iterator();

			while (it.hasNext()) {
				children.add((ItemType) it.next());
			}
			for (ItemType itemType : children) {
				result += getLink(itemType, cat);
//				result+="<a href='" + itemType.getItemType() + "' >" + "-- " + itemType.getTypeName().toString() + "</a><br />";
				Set itemTypes1 = itemType.getItemTypes();
				List<ItemType> children1 = new ArrayList<ItemType>();
				Iterator it1 = itemTypes1.iterator();
				while (it1.hasNext()) {
					children1.add((ItemType) it1.next());
				}
				for (ItemType itemType1 : children1) {
					System.out.println("  --- "
							+ itemType1.getTypeName().toString());
					result += getLink(itemType1, cat);
//					result+="<a href='" + itemType1.getItemType() + "' >" +"  --- " + itemType1.getTypeName().toString() + "<br />";
				}

			}

		}
		return result;
	}
	
	private String getLink(ItemType item, long selectedCat){
		String linkHTML = "<a " + getSelectedStyle(item.getItemType(), selectedCat) + 
				" href='?";
		linkHTML += "cat=" + item.getItemType();
		linkHTML += "' >";
		linkHTML += getPrefix(item);
		linkHTML += item.getTypeName().toString();
		linkHTML += "</a><br />";
		return linkHTML;
	}
	
	private String getPrefix(ItemType item) {
		String prefix = "";
		for (int i = 1; i < item.getItemLevel(); i++) {
			prefix += "--";
		}
		return prefix;
	}
	
	private String getSelectedStyle(long currCat, long selCat) {
		String style = "";
		System.out.println(selCat);
		if (currCat == selCat) {
			style = "style=\"color:red;\"";
		}
		return style;
	}

}
