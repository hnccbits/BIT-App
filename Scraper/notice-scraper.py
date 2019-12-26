

# Script for scraper ...fot Notice from     (https://www.bitsindri.ac.in/) 
import requests
import lxml
import bs4
import io
from urllib.error import HTTPError , URLError 
from requests.exceptions import ConnectionError


# this function returns all notice and link for all notice in python dictonary form. 

def getNotice():
    json_data = {
        'Success':False,  # if any occurs then Success contain False .
        'data':[],
    }
    # json_data is python dictonary containing the data.
    try:
        htmlbody = requests.get("https://www.bitsindri.ac.in/")
    except requests.exception.RequestException as e:
        print(e)
        json_data['Success'] = False
        return json_data
    soup = bs4.BeautifulSoup(htmlbody.text,'lxml')
    notice = soup.find_all(class_ = 'menu')
    notice = notice[0]
    if not notice:
        json_data['Success'] = False
        return json_data
    l = []
    for n in notice:
        l.append(n)

    # l[0] is just a string so ...we cant apply getText and find on a string 
    # so first we have to delete first element .
    l.remove(l[0])
    for note in l:
        json_data['Success'] = True
        d = {}
        text = note.getText()
        link = note.find('a').get('href')
        d['text'] =text
        d['link'] = link        
        json_data['data'].append(d)
    return json_data

    
# function calling ...
print(getNotice())
        