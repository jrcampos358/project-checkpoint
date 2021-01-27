/* eslint-disable jsx-a11y/no-static-element-interactions */
/* eslint-disable no-script-url */
/* eslint-disable jsx-a11y/anchor-is-valid */
import React, { Component } from 'react'
import Header from '../Header'

import './styles.scss'

class Outline extends Component {
  render() {
    return (
      <div className="container">
        <Header />
        <main role="main" className="flex-shrink-0">
          <div className="container-fluid">{this.props.children}</div>
        </main>
      </div>
    )
  }
}

export default Outline
