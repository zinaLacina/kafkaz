import React, {Component} from 'react';
import PropTypes from 'prop-types';
import api from "../api";

class PodHealth extends Component {

    state = {
        data: [],
        intervalIsSet : false
    };

    async componentDidMount() {
        this.getHealth();
        // if (!this.state.intervalIsSet) {
        //     let interval = setInterval(this.getHealth, 10000);
        //     this.setState({ intervalIsSet: interval });
        // }
    }
    convertData (data) {
        return Object.keys(data).map(key => {
            return data[key];
        })
    }

     getHealth = () => {
        try{
            api.get("/actuator")
                .then(res => this.setState({ data: this.convertData(res.data._links) || [] }));
        }catch (err){
            console.log(err)
        }

            // .then(response => console.log(response.data))
        // fetch("http://localhost:3001/api/getData")
        //     .then(data => data.json())
        //     .then(res => this.setState({ data: res.data }));
    };
    render() {
        const { data } = this.state;
        console.log("The content {}",data)
        return (
            <div>
                {

                    data !== null && data.map((item, i) => {
                        // console.log(item)
                        return (<p key={i}> {item.href} </p>)
                    })
                }
            </div>
        );
    }
}

PodHealth.propTypes = {};

export default PodHealth;