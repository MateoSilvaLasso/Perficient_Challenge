import React from 'react'
import {Route, Routes, BrowserRouter  } from  'react-router-dom';
import App from '../App';

function Router() {
  return (
    <BrowserRouter>
        <Routes>
            <Route path="/" element = {<App/>}/>
        </Routes>
    </BrowserRouter>
  )
}

export default Router
