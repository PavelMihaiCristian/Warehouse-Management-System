package ClientModel;

import java.rmi.RemoteException;
import java.util.ArrayList;

import util.Order;
import util.Pallet;
import util.Pickers;
import ServerController.ServerInterface;

public class ClientModel {

	
	
	public ClientModel() {
		
	}
	
	//update 02/12/2016
	
	public String getProductInformation(ArrayList<Object> list){
		String str="";
		if(list.size()==0) return str;
		for(int i=0;i<list.size();i++){
			str+=list.get(i)+"\n";
		}
		return str;
	}
	
	public String getProductsCloseToExpiryDate(ArrayList<Object> list){
		String str="";
		if(list.size()==0) return str;
		for(int i=0;i<list.size();i++){
			Pallet pallet=(Pallet) list.get(i);
			str+=pallet.toStringForListOfExpiry()+"\n";
		}
		return str;
	}

	public Pickers[] getAllPickers(ArrayList<Pickers> allPickers) {
		Pickers[] result=new Pickers[allPickers.size()];
		if(allPickers.size()==0){
			return result;
		}
		return allPickers.toArray(result);
	}
	
	public String getAllPickersByName(ArrayList<Pickers> allPickers){
		StringBuilder str=new StringBuilder();
		for(int i=0;i<allPickers.size();i++){
			str.append(allPickers.get(i).toString2()+"\n");
		}
		return str.toString();
	}
	
	//update 06/12/2016
	public Order[] getOrders(ArrayList<Order> orders){
		Order[] list=new Order[orders.size()];
		return orders.toArray(list);
	}

	public String getPalletsAndPositions(Pallet[] palletsAndLocations) {
		StringBuilder str=new StringBuilder();
		for(int i=0;i<palletsAndLocations.length;i++){
			str.append(palletsAndLocations[i].toStringForRegistraring());
			str.append("\n");
		}
		return str.toString();
	}
	//end
}
