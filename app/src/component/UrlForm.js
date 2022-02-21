import React, { Component } from 'react';
import { Button, Form, FormGroup, Input, Label } from 'reactstrap';
import ShortUrlAnchor from './ShortUrlAnchor';


export default class UrlForm extends Component{
  constructor(props) {
      super(props);
        this.state = {
            fullurl: '',
            tinyUrlList: [

            ],
            isEmptyList: true,
            error: null
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }

     async componentDidMount(){
      await fetch('/api/urlshortner')
            .then(res=> res.json())
            .then((result)=> {
              const notEmpty = result.length <1;
              this.setState(
                {
                  tinyUrlList: result,
                  isEmptyList: notEmpty
                }
              );
            });
           
      }
    
      handleChange(event){
        const target = event.target;
        const value = target.value;
        this.setState({fullurl: value});
      }
    
      async handleSubmit(event) {
        event.preventDefault();
        const requestOptions = {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          } ,
          body: this.state.fullurl
      };
        
        await fetch('/api/urlshortner', requestOptions)
        .then(response =>{
          if(response.ok){
            return response.json();
          }else{
            throw new Error('Plese Enter a valid url');
          }
          
        } )
        .then((data) => this.setState(
          { fullurl: '' ,
            tinyUrlList: data,
            isEmptyList: false,
            error: null
          }
        ))
        .catch(error=>this.setState({
          fullurl: '',
          error: error
        }));
      }
    
      render(){
          return (
            <div className="App">
              <Form className="App-form" onSubmit={this.handleSubmit}>
                <div className="error">
                {this.state.error != null && <p className="errortext">{this.state.error.message}</p> }
                </div>
              
              <FormGroup className="App-form-formgroup">
                  <Label>
                      <Input type="text" className="input-field" value = {this.state.fullurl} onChange = {this.handleChange} />
                  </Label>
                  
                </FormGroup>
      
                <FormGroup className="App-form-formgroup">
                  <Button className="submit-button" type="submit">Save</Button>
                </FormGroup>
      
              </Form>
              <div className="existing-content">
              {this.state.isEmptyList ?'': this.state.tinyUrlList.map((tinyUrl)=>
                    <ShortUrlAnchor tinyUrl={tinyUrl} />
              )}
              </div>
              
              </div>
          );
        
      }
}