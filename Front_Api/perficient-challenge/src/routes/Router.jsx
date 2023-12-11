import React from 'react'
import {Route, Routes, BrowserRouter  } from  'react-router-dom';
import Tasks from '../pages/tasks';
import Login from '../pages/login';

function Router() {
  return (
    <BrowserRouter>
        <Routes>
            <Route path="/" element = {<Login/>}/>
            <Route path="/app" element = {<Tasks/>}/>
        </Routes>
    </BrowserRouter>
  )
}

export default Router
