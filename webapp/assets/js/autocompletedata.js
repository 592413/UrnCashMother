


$( function() {
    var availableTags = 
						['Andaman and Nicobar',
						 'Andhra Pradesh',
						 'Arunachal Pradesh',
						 'Assam','Bihar',
						 'Chandigarh',
						 'Chhattisgarh',
						 'Daman and Diu', 
						 'Delhi','Goa',
						 'Gujarat',
						 'Haryana',
						 'Himachal Pradesh',
						 'Jammu And Kashmir',
						 'Jharkhand',
						 'Karnataka',
						 'Kerala',
						 'Lakshadweep',
						 'Madhya Pradesh',
						 'Maharashtra',
						 'Manipur',
						 'Meghalaya',
						 'Mizoram',
						 'Nagaland',
						 'Orissa',
						 'Pondicherry',
						 'Punjab',
						 'Rajasthan',
						 'Sikkim',
						 'Tamilnadu',
						 'Tripura',
						 'Uttarakhand',
						 'Uttar Pradesh',
						 'West Bengal'
						];
    $(".tags").autocomplete({
		
      source: availableTags
    });
  } );
 