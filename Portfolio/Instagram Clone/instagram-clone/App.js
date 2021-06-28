//02:03:00

import {StatusBar} from 'expo-status-bar'
import {View,Text} from 'react-native' 
import { NavigationContainer } from '@react-navigation/native'
import { createStackNavigator } from '@react-navigation/stack'
import React,{Component} from 'react'
import LandingScreen from './components/auth/Landing'
import RegisterScreen from './components/auth/Register'
import MainScreen from './components/Main'
import {Provider} from 'react-redux'
import {createStore,applyMiddleware} from 'redux'
import rootReducer from './redux/reducers'
import thunk from 'redux-thunk'
import AddScreen from './components/main/Add'


import firebase from 'firebase'
import Register from './components/auth/Register'
// For Firebase JS SDK v7.20.0 and later, measurementId is optional

const store = createStore(rootReducer,applyMiddleware(thunk))

const firebaseConfig = {
  apiKey: "AIzaSyA0VL2S7Gr7TbFU57O33kmn2nW1aiNFlRw",
  authDomain: "instagram-clone-e3168.firebaseapp.com",
  projectId: "instagram-clone-e3168",
  storageBucket: "instagram-clone-e3168.appspot.com",
  messagingSenderId: "528432856968",
  appId: "1:528432856968:web:b3129d763ade9bc321843d",
  measurementId: "G-1XX1FX9CMV"
};
if(firebase.apps.length===0)
{
  firebase.initializeApp(firebaseConfig)  
}
const Stack = createStackNavigator();

export class App extends Component {
  constructor(props)
  {
    super(props);
    this.state={
      loaded:false
    }
  }
  componentDidMount()
  {
    firebase.auth().onAuthStateChanged((users)=>{
      if(!users)
      {
        this.setState({
          loggedIn: false,
          loaded: true
        })
      }
      else{
        this.setState({
          loggedIn: true,
          loaded: true
        })
      }
    })
  }


  render() {
    const {loggedIn,loaded } = this.state
    if(!loaded)
    {
      return(
        <View style={{flex: 1,justifyContent: 'center'} }>
          <Text> Loading..</Text>
        </View>
      )
    }
    if(!loggedIn)
  {  
    return (
      <NavigationContainer>
      <Stack.Navigator initialRouteName = "Landing">
        <Stack.Screen name = "Landing" component ={LandingScreen} options ={{headerShown:false}}>
        </Stack.Screen>
        <Stack.Screen name = "Register" component ={RegisterScreen} options ={{headerShown:false}}>
        </Stack.Screen>
      </Stack.Navigator>
    </NavigationContainer>
  )
}
else{
  return(
    <Provider store={store}>
      <NavigationContainer>
           <Stack.Navigator initialRouteName = "Main">
        <Stack.Screen name = "Main" component ={MainScreen} options ={{headerShown:false}}/>
        <Stack.Screen name = "Add" component ={AddScreen}/>
      </Stack.Navigator>
      </NavigationContainer>
    </Provider>
  )

}
  }
}

export default App
