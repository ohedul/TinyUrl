import React, { Component } from 'react';

class ShortUrlAnchor extends Component{
  
    constructor(props){
        super(props);
    }

    render(){
        return(
            <div>
            <a href={`${process.env.REACT_APP_API_KEY + '/api/urlshortner/'+ this.props.tinyUrl.key}`} target="_blank" rel="noopener noreferrer">
              {this.props.tinyUrl.key}
            </a>
          </div>
        )
    }
}
export default ShortUrlAnchor;
