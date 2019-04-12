
# coding: utf-8

# In[2]:


import simpy


# In[7]:


Lines = {'Baggage drop-off':1,'Check-In':1,'Security':1,
         'Passport control':1, 'Shops':1,'Boarding':1,
         'Baggage pick-up':1,'Customs':1,'Other':0}
Flights = {'flight':{'people':0,'departure':'23:00','Airplane':233}}
Airport = 'Schipol'


# In[14]:


class Person(object):
    def __init__(self,env):
        self.env = env
        self.action = env.process(self.start())
    def start(self):
        print('New person created')#Might delete later
    
    def register(self, duration):
        try:
            yield self.env.timeout(duration)
        except simpy.Interrupt:
            print('Was interrupted, No registration')


# In[ ]:


#Each line has an average delay time and a dynamic amount of resources
#Each person has an average speed (From point A to B)
# and an average speed at each line (aka security)
#People can be in clusters (families, friends, etc) which most likely increase the delay in everyone
##Can we increase speed in l Line if the resources have certain properties like languages and we send a person with that language there?
##Can we create extra services to make friends/networking based on their data
#

