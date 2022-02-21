import React, { Component } from 'react';

class ShortUrlAnchor extends Component{
  
    constructor(props){
        super(props);
    }

    render(){
        return(
            <div>
            <a href={`http://localhost:8012/api/urlshortner/${this.props.tinyUrl.key}`} target="_blank" rel="noopener noreferrer">
              {this.props.tinyUrl.key}
            </a>
          </div>
        )
    }
}
export default ShortUrlAnchor;