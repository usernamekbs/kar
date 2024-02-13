import React,{ useState,useEffect } from 'react'; 
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import useUserStore from "../stores/useUserStore"
import { Link } from 'react-router-dom';
import axios from 'axios'

const Navbar = () => {
    const { user, removeUser} = useUserStore();
    const [categoryList,setCategoryList] = useState();
    
    const logOutHandler = () => {
        removeUser(user)
    }

    const fetchCategory = async () =>{
        try {  
            const result = await axios.get(`http://localhost:8080/api/category/list`)
            setCategoryList(result.data)
          } catch (error) {
            console.log(error);
          }
    }

    useEffect(() => { 
        fetchCategory() 
    }, []);  
    
    return (
        <Box sx={{ flexGrow: 1}}>
        
        <AppBar position="static" style={{backgroundColor:'#79edff'}}>
            <Toolbar>
            <IconButton
                size="large"
                edge="start"
                color="inherit"
                aria-label="menu"
                sx={{ mr: 2,color:'black' }}
            >
                <MenuIcon />
            </IconButton>
            {categoryList && categoryList.map((category) => (
                <Typography key={category.id} variant="h8" component="div" sx={{ flexGrow: 0.4, marginLeft:'10px' }}>
                    <Link to={{
                            pathname: `/category/${category.id}/${category.name}`,
                        }}
                        style={{ textDecoration: "none", color:'black'}}>
                        {category.name} 
                    </Link>
                    
                </Typography>
                
            ))} 
            
            {user && user.roles.find((r)=> r==='ADMIN') &&
                <Link to={{
                        pathname: `/user`,
                    }}
                    style={{ textDecoration: "none", color:'black',marginLeft:'10px'}}>
                    user
                </Link>
            }

            {/* {user && user.roles.find((r)=> r==='MANAGER') &&
                <Link to={{
                        pathname: `/category/manager`,
                    }}
                    style={{ textDecoration: "none", color:'white',marginLeft:'20px'}}>
                    MANAGER
                </Link>
            } */}
            {user ? 
                <Link to={{
                       pathname: `/user/view/${user.id}`,
                    }} 
                    style={{ textDecoration: "none", color:'black',marginLeft:'10px'}}
                >{user.username}정보</Link>
                : 
                null
            }
            
            {user ?
                <Button style={{marginLeft: '75%',color:'black'}} color="inherit" onClick={() => logOutHandler()}>로그아웃</Button>
                :
                <Button style={{marginLeft: '75%',color:'black'}} color="inherit" href={`/login`}>Login</Button>
            }    
                    
            </Toolbar>
        </AppBar>
        </Box>
    );

}
export default Navbar;