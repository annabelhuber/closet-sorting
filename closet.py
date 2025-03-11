'''closet.py '''

import pandas as pd

''' create a list of lists
outer list: each entry is one article of clothing
inner list: [color, brand, size, shirt length, sleeve length]'''

closet = []
closet.append(['clothingType', 'color', 'brand', 'size', 'shirtLength', 'sleeveLength', 'misc', 'location', 1])


def addShirt (clothingType, color, brand, size, shirtLength, sleeveLength, misc, location = None):
    '''create a new entry with all the specifications'''

    item = [clothingType, color, brand, size, shirtLength, sleeveLength, misc, location]

    #add a numerical value to the end of each entry to make them identifiable by #
    entryNo = int(len(closet)) + 1

    item.append(entryNo)

    #append the new item to the closet
    closet.append(item)

    return closet


def search (category, attribute):
    ''' searches through closet to find any/all articles of clothing that match the desired parameters
    category: the parameter in which the desired attribute falls under e.g. sleeve length
    attribute: the desired attribute e.g. long sleeve
    
    returns a list of all entries in closet that fit the specifications given here'''
    
    # create a list to hold the matching items
    itemList = []
    # find which position parameter to search for
    for i in closet[0]:
        #loop through the first entry in closet (format entry)
        if category == i:
            #if the given category matches the format, use the position (para)
            para = closet[0].index(i)
    
    for j in range(0, len(closet), 1):
        #loop through all entries in closet
        if closet[j][para] == attribute:
            #if the given attribute at position para matches the entry, add that item to the list
            itemList.append(closet[j])

    return itemList


def showCloset(subList = None):
    ''' prints closet in a nicely formatted manner'''
    if subList == None:
        subList = closet

    table = pd.DataFrame(subList)
    print(table)


def showArticle(articleNo):
    print(closet[articleNo - 1])
    # show photo of corresponding article




    






