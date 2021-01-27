import React from 'react'
import { render } from 'react-dom'
import { act } from 'react-dom/test-utils'
import '@testing-library/jest-dom'

import Outline from '../components/Outline'

let container

// before each test, create `div` element
beforeEach(() => {
  container = document.createElement('div') // <div>...
  // elem.setAttribute('className', 'app') // <div id="app">...
  document.body.appendChild(container) // <body><div>...
})

afterEach(() => {
  document.body.removeChild(container)
  container = null
})

// test `App` component with `name` prop
test('Header component with className="app"', () => {
  // render and prepare
  act(() => {
    render(<Outline />, container)
  })

  const clem = container.querySelector('span') // <h1>

  // check for `<span>` element content
  expect(clem).toHaveTextContent('Project : Payment via Paypal')
})
