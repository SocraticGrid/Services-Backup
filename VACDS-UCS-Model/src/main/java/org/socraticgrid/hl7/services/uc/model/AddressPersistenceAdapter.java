/**
 * 
 */
package org.socraticgrid.hl7.services.uc.model;


/**
 * @author steven
 * @created Jan 27, 2014
 * 
 * Takes any of the three address types and provides methods for CRUD
 * 
 * This adapter has a two separate functions
 * 	1) Persist a BaseAddress type to the Address table
 * 	2) Retrieve a BaseAddress type from the Address table
 * 
 * The two functions are not symmetric - in other words for example you cannot create an adapter
 * for a PhysicalAddress then have the adapter build and return a PhysicalAddress (e.g. getMaskedAddress())
 * This is on purpose - this class is only to be used as an interface to/from persistence and not as a general
 * facade for BaseAddress types - it is not for modifying existing types or constructing new ones.
 */
public class AddressPersistenceAdapter {
	
	public <T extends BaseAddress> AddressPersistenceAdapter(T maskedAddress){
		this.maskedAddress = maskedAddress;
	}
	
	AddressPersistenceAdapter() {};

	private BaseAddress maskedAddress;
	// So an address can be built from persistence
	@SuppressWarnings("unchecked")
	public <T extends BaseAddress> T getMaskedAddress() {
		return (T) buildAddress();
	}
	// So an address can be supplied by the application
	public <T extends BaseAddress> void setMaskedAddress(T address) {
		this.maskedAddress = address;
	}


	AddressType addressType;
	public AddressType getAddressType() {
		return maskedAddress.getAddressType();
	}
	@SuppressWarnings("unused") // This is only used by Persistence
	private void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}
	
	String addressId;
	public String getAddressId() {
		return maskedAddress.getAddressId();
	}
	@SuppressWarnings("unused") // This is only used by Persistence
	private void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
	String serviceId;
	public String getServiceId() {
		switch(maskedAddress.getAddressType()){
			case Physical: return ((PhysicalAddress)maskedAddress).getServiceId();
			
			default:
				return null;
		}
	}
	@SuppressWarnings("unused") // This is only used by Persistence
	private void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	String address;
	public String getAddress() {
		switch(maskedAddress.getAddressType()){
			case Physical: return ((PhysicalAddress)maskedAddress).getAddress();
			
			default:
				return null;
		}
	}
	@SuppressWarnings("unused") // This is only used by Persistence
	private void setAddress(String address) {
		this.address = address;
	}
	
	String name;
	public String getName() {
		switch(maskedAddress.getAddressType()){
			case Group: return ((GroupAddress)maskedAddress).getName();

			case Party: return ((PartyAddress)maskedAddress).getName();
			
			default:
				return null;
		}
	}
	@SuppressWarnings("unused") // This is only used by Persistence
	private void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("unchecked")
	private <T extends BaseAddress> T buildAddress() {
		switch(addressType){
		case Group: GroupAddress groupAddr = new GroupAddress();
		groupAddr.setAddressId(addressId);
		groupAddr.setName(name);
		return (T) groupAddr;

		case Party: PartyAddress partyAddr = new PartyAddress();
		partyAddr.setAddressId(addressId);
		partyAddr.setName(name);
		return (T) partyAddr;

		case Physical: PhysicalAddress physicalAddr = new PhysicalAddress();
		physicalAddr.setAddressId(addressId);
		physicalAddr.setAddress(address);
		physicalAddr.setServiceId(serviceId);
		return (T) physicalAddr;

		default:
			return null;
		}
	}
	
}
