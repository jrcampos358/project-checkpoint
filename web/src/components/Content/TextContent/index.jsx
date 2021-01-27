/* eslint-disable jsx-a11y/anchor-is-valid */
import React, { Component } from 'react'

import './styles.scss'

class TextContent extends Component {
  render() {
    return <div className="container-fluid">{this.props.children}</div>
  }
}

export default TextContent
