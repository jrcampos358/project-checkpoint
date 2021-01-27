/* eslint-disable react/no-unescaped-entities */
/* eslint-disable jsx-a11y/anchor-is-valid */
import React from 'react'

import './styles.scss'

const Header = () => (
  <div className="app">
    <nav className="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
      <span className="navbar-brand mb-0 h1">Project : Payment via Paypal</span>
      <button
        className="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarsExampleDefault"
        aria-controls="navbarsExampleDefault"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon" />
      </button>

      <div className="collapse navbar-collapse" id="navbarsExampleDefault">
        <div className="navbar-nav ml-auto">
          <a href="/" className="nav-item nav-link">
            Home
          </a>
          <a href="#" className="nav-item nav-link">
            Login
          </a>
        </div>
      </div>
    </nav>
  </div>
)

export default Header
