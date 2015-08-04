function StaffMember(name,discountPercent){
	this.name = name;
	this.discountPercent = discountPercent;
};

var sally = new StaffMember("Sally",5);
var bob = new StaffMember("Bob",10);

// Crie a si mesmo como 'me' com um desconto de funcionario de 20%
var me = new StaffMember("me",20);

var cashRegister = {
	total:0,
	lastTransactionAmount: 0,
	add: function(itemCost){
		this.total += (itemCost || 0),
		this.lastTransactionAmount = itemCost
	},
	scan: function(item,quantity){
		switch (item){
			case "ovos": this.add(0.98 * quantity); break;
			case "leite": this.add(1.23 * quantity); break;
			case "revista": this.add(4.99 * quantity); break;
			case "chocolate": this.add(0.45 * quantity); break;
		}
		return true;
	},
	voidLastTransaction : function(){
		this.total -= this.lastTransactionAmount;
		this.lastTransactionAmount = 0;
	},
	applyStaffDiscount: function(employee){
		this.total = this.total-(this.total * (employee.discountPercent/100));
	}

};

cashRegister.scan('ovos',1);
cashRegister.scan('leite',1);
cashRegister.scan('revista',3);
cashRegister.applyStaffDiscount(me);

// Mostre o total da conta
console.log('Sua conta é '+cashRegister.total);