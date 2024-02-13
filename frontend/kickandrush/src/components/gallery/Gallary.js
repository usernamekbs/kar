import React, { useState, useEffect } from 'react';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import { CardMedia } from '@mui/material';
import Button from '@mui/material/Button';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import TableContainer from '@mui/material/TableContainer';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';
import { Container, TextField } from "@mui/material";
import { red } from '@mui/material/colors';
import { Link,useParams } from 'react-router-dom';
import axios from "axios";
import useUserStore from "../../stores/useUserStore"

const Gallery = () => {
    const [galleryList,setGalleryList] = useState([]);
    const [galleryTotal,setGalleryTotal] = useState(0); //전체 개시글수
    const [galleryTotalPages,setGalleryTotalPages] = useState(0) //전체 페이징 수
    const [galleryPage,setGalleryPage] = useState(1)
    const [searchType, setSearchType] = useState("Title");
    const [searchText, setSearchText] = useState("");
    const category = useParams();
    const { user } = useUserStore();
    
    const handleChange = (e,value) => {
        setGalleryPage(value); 
      };

    const fetchGallery = async () => {
      
        try {  
            const result = await axios.get(`http://localhost:8080/api/gallery/list`,
            {params: {  
                searchText : searchText,
                searchType : searchType
              },
            })    
            setGalleryList(result.data.content) 
            setGalleryTotal(result.data.totalElements) 
            setGalleryTotalPages(result.data.totalPages)
        } catch (error) {
          console.log(error); 
        }
      };

    useEffect(() => {
        fetchGallery()   
    }, [galleryPage]); 

    return (
        <>  
            <TableContainer component={Paper} style={{marginTop:'20px'}}>
                <Typography variant="h5" gutterBottom >
                게시글 개수 : {galleryTotal}
                </Typography>
                   
               
                {galleryList.map((gallery)=>{
                    return(
                        
                    <Card sx={{ maxWidth: 300,margin: '4%', float:'left' }}  key={gallery.id}>
                        <CardHeader
                            // avatar={
                            // <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
                            //     R
                            // </Avatar>
                            // }
                            title={"작성자:"+gallery.username}
                            subheader={"카테고리:"+gallery.name}
                        />
                        <Link style={{textDecoration: 'none'}} to={`/category/${category.categoryId}/gallery/view/${gallery.id}`}>
                            <CardMedia
                                sx={{ height: 250,width:250 }}
                                image={require(`../../thumb/${gallery.origName}`)}
                            /> 
                            <CardContent>   
                                <Typography gutterBottom variant="h5" component="div">
                                    {gallery.title}
                                </Typography>
                            </CardContent>
                        </Link>
                    </Card>
                )
                })
                }
            </TableContainer>
            {user &&
                <Link to={`/category/${category.categoryId}/gallery/create`} style={{ textDecoration: "none", color:'white' }}>
                    <Button variant="contained" color="primary">저장</Button>
                </Link>
            }
            <Stack spacing={2}>
                <Pagination style={{display:"flex",justifyContent:"center",marginTop:'10px'}} count={galleryTotalPages} page={galleryPage} onChange={handleChange} variant="outlined" color="primary" />
            </Stack>
            <Container maxWidth="md" sx={{ mt: 2 }}>
                <Select
                labelId="demo-simple-select-autowidth-label"
                id="demo-simple-select-autowidth"
                onChange={(e)=> setSearchType(e.target.value)}
                autoWidth
                label="search"
                defaultValue="Title"   
                value={searchType}
                >
                <MenuItem value="Title"><em>제목</em></MenuItem>
                <MenuItem value="Content">내용</MenuItem>
                <MenuItem value="Username">유저이름</MenuItem>
                </Select>
                <TextField  type="search" id="searchText" label="Search" sx={{ width: 600 }} onChange={(e) => setSearchText(e.target.value)}/>
                <Button style={{height:"56px"}} variant="contained" onClick={fetchGallery}>검색</Button>
            </Container>
        </>
      );

}
export default Gallery;