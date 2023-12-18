import React from 'react'
import Authentication from '../components/authentication'
import axios from  '../config/axios'
import { context } from '../context/context'
import { useEffect, useState } from 'react'

const login = () => {

  const[userEdit, setUserEdit] = useState({token:""})

  const addUser = async (user) =>{
    try {
      const res = await axios.post("/login/create", user);
        if (res.status === 200) {
            setUserEdit(res.data);
            console.log(res.status);
            console.log(userEdit);
        }
      } catch (e) {
          console.log(e);
      }
  }


  return (
    <context.Provider value={{ userEdit, setUserEdit }}>
          <Authentication addUser={addUser} userEdit={userEdit}/>
    </context.Provider>
  )
}

export default login
