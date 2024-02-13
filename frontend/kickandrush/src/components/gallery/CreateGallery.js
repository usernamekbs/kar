import React, { useState, useEffect } from 'react';
import TextField from '@mui/material/TextField';
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import Button from "@mui/material/Button";
import { Typography } from "@mui/material";
import axios from "axios";
import { useNavigate  } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import AddToPhotosIcon from '@mui/icons-material/AddToPhotos';
import ImageList from '@mui/material/ImageList';
import ImageListItem from '@mui/material/ImageListItem';
import ImageListItemBar from '@mui/material/ImageListItemBar';
import useUserStore from "../../stores/useUserStore"
import CardMedia from '@mui/material/CardMedia';

const CreateGallery = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const categoryId = useParams().categoryId;
    const [readerImages,setReaderImages] = useState();
    const [thumb,setThumb] =useState();
    const navigate = useNavigate();
    const { user } = useUserStore();

    const handleChange = e => {
        const images = e.target.files[0];
        setThumb(images)
        //파일을 읽어오기위한 객체  
        const reader = new FileReader();
        if(images){
            reader.readAsDataURL(images);
        }
        reader.onload = () => {
            setReaderImages(reader.result);
        };
    };

    const createGallery = async(e) => {
        e.preventDefault()
        const formData = new FormData();
        const config = {
            headers: {
                "content-type": "multipart/form-data",
                'x-access-token': user.accessToken
            }
        }
        const data = {
            title       : title,
            content     : content,
            userId      : user.id,
            categoryId  : categoryId
        }
        console.log(data) 
        console.log(readerImages)   
        formData.append('file', thumb); 
        formData.append("requestGalleryDto", new Blob([JSON.stringify(data)], {
            type: "application/json"
        }));

        try {
            await axios.post(`http://localhost:8080/api/gallery/create/`,formData,config);
            navigate('/category/1/post')
        
        } catch (error) { 
            console.log(error);
        }
    };
   
    useEffect(() => {
    }, []); 

   

    return (
        <Card sx={{ marginTop:'2%',marginLeft:'25%',minWidth: 275, maxWidth: "50vw", padding: 5 }}>
            <Box>
                <Typography variant="h5">게시글 쓰기</Typography>
            </Box>
           
            <CardMedia
                sx={{ height: 300,width:300}}
                image={readerImages} 
                title="green iguana"
            />
            <Box height={"100vh"}>
                <TextField
                fullWidth
                label="제목"
                type="text"
                variant="standard"
                onChange={(e) => setTitle(e.target.value)}
                />
                <TextField
                fullWidth
                label="내용"
                type="text"
                variant="standard"
                onChange={(e) => setContent(e.target.value)}
                />
                <Button style ={{marginTop:'20px',width:'100%'}}variant="contained" component="label" color="primary">
                    {" "}
                    <AddToPhotosIcon /> Upload a file
                        <input type="file" 
                            onClick={(e)=>handleChange(e)}
                            onChange={handleChange} hidden/>
                </Button>
            </Box>
            
            <Button fullWidth onClick={createGallery} variant="contained">
                저장
            </Button>
            
        </Card>
      );

}
export default CreateGallery;