import React, { Component } from 'react';
import UrlForm from './component/UrlForm';
import { BrowserRouter as  Router, Routes, Route} from 'react-router-dom';
import './App.css';

class App extends Component {

  render(){
    return(
      <UrlForm />
      
    )
  }
}

export default App;
