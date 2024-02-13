import React, { useState, useEffect } from 'react';
import Card from "@mui/material/Card";
import Button from "@mui/material/Button";
import { Typography } from "@mui/material";
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import axios from "axios";
import { useParams,useNavigate,Link  } from 'react-router-dom';
import useUserStore from "../../stores/useUserStore"

const ViewUser = () => {
    const id = useParams().id;
    const [users, setUsers] = useState({});
    const navigate = useNavigate();
    const { user } = useUserStore();

   
    useEffect(() => { 
        const oneUser = async () => {
            try {
              const result = await axios.get(`http://localhost:8080/api/user/view/${id}`,{ headers: {"Authorization" : `Bearer ${user.accessToken}`} });
              setUsers(result.data)
              } catch (error) { 
              console.log(error);      
            }
          };

          oneUser();
    }, []);  

    const deleteUser = async () => {
        try {
          console.log(id)
            await axios.delete(`http://localhost:8080/api/user/delete/${id}`,{ headers: {"Authorization" : `Bearer ${user.accessToken}`} });
            navigate(`/user`)
        } catch (error) { 
          console.log(error);
        }
    };

    return (
        <Card sx={{ marginTop:'2%',marginLeft:'25%',minWidth: 275, maxWidth: "50vw", padding: 5 }}>
           <CardContent key={users.id}>
            <Typography variant="h5" component="div">
                유저 번호 : {users.id}
            </Typography>
            <Typography variant="body1">
                유저 이름 : {users.username}
            </Typography>
            <Typography variant="body1">
                유저 닉네임 : {users.nickname}
            </Typography>
            <Typography variant="body1">
                유저 이메일 : {users.email}
            </Typography>
            <Typography variant="body1">
                유저 권한 : {users.role}
            </Typography>
            {users.roles && users.roles.map((role)=>{
                return <span>{role.name} </span>
            })}
            
        </CardContent>
        <CardActions>
            <Link to={`/user/update/${id}`} state={{ user: users}}> 
              <Button variant="contained" color="success">수정</Button>
            </Link>
            <Button variant="contained" color="error" onClick={deleteUser}>삭제</Button>
        </CardActions>
        </Card>
      );
}

export default ViewUser;