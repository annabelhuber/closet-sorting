'''closetAdditions.py'''

import closet as cl

cl.addShirt('drawer', 'black', 'zara', 'm', 'cropped', 'tank', 'lacy')
cl.addShirt('underBed','white', 'princess polly', 'm', 'cropped', 'tee', 'tie front')
cl.addShirt('hanging','blue', 'zara', 'm', 'mid', 'tube', 'denim')

cl.showCloset()

zaraShirts = cl.search('brand', 'zara')
cl.showCloset(zaraShirts)