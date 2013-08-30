package wildness.mod.data.zone;

import java.util.List;

public class ClaimOwners {

	protected String owner;
    protected List<String> peoples;
    protected String Messages;
    protected boolean trespass;
    protected boolean vandalism;
    
    public ClaimOwners(String owner2, List<String> peoples2, String messages2,
			boolean trespass2, boolean vandalism2) {
		this.owner = owner2;
		this.peoples = peoples2;
		this.Messages = messages2;
		this.trespass = trespass2;
		this.vandalism = vandalism2;
	}    
    
    
    

}
