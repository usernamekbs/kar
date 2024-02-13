import React, { useState, useEffect } from 'react';
import TextField from '@mui/material/TextField';
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import Button from "@mui/material/Button";
import { Typography } from "@mui/material";
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import axios from "axios";
import { useParams,useNavigate,Link  } from 'react-router-dom';
import useUserStore from "../../stores/useUserStore"

const ViewGallery = () => {
    const id = useParams().id;
    const navigate = useNavigate();
    const [gallery, setGallery] = useState({});
    const [likeCount, setLikeCount] = useState(0);
    const { user } = useUserStore();
    const categoryId = useParams().categoryId;
    const headers = {
      'Authorization': user.accessToken
    }
    
    const oneGallery = async () => {
        try {
          const result = await axios.get(`http://localhost:8080/api/gallery/view/${id}`);
          setGallery(result.data)
          console.log(result.data)  
        } catch (error) { 
          console.log(error);
        }
      };
    
    const deleteGallery = async () => {
        try {
            await axios.delete(`http://localhost:8080/api/gallery/delete/${id}`,headers);
            navigate(`/category/${categoryId}/gallerys`)
        } catch (error) { 
          console.log(error);
        }
    };

    

    useEffect(() => {

        oneGallery();
    }, []);  

    return (
        <Card sx={{ marginTop:'2%',marginLeft:'25%',minWidth: 275, maxWidth: "50vw", padding: 5 }}>
           <CardContent key={gallery.id}>
            <Typography variant="h5" component="div">
                제목:{gallery.title}
            </Typography>
            <Typography variant="body1">
                내용:{gallery.content} 
            </Typography>
            <Typography variant="body1">
                작성자:{gallery.username}
            </Typography> 
            <Typography variant="body1">
            </Typography>
            <Typography variant="body1">
            </Typography>
        </CardContent>
        <CardActions>
            <Link to={`/category/${categoryId}/gallery/update/${id}`} state={{ user: user}}> 
              <Button variant="contained" color="success">수정</Button>
            </Link>
            <Button variant="contained" color="error" onClick={deleteGallery}>삭제</Button>
        </CardActions>
        </Card>
      );
}

export default ViewGallery;