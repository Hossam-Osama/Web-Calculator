<template>
    <div class="calculator">
      <div id="text">
        <div id="history">{{previous||'0'}}</div>
        <div id="output">{{temp||'0'}}</div>
      </div>
  
      <div id="buttons">
        <div >
          <button @click="percent" class="operation">%</button>
          <button @click="Delete" class="operation">CE</button>
          <button @click="DeleteAll" class="operation">C</button>
          <button @click="remove" class="operation">⌫</button>
        </div>
        <div >
          <button @click="reverse()" class="operation"><sup>1</sup>&frasl;<sub>x</sub></button>
          <button @click="square()" class="operation"><sub>x</sub>2 </button>
          <button @click="root()" class="operation">&#x221A; x</button>
          <button @click="divide()" class="operation">&#xf7;</button>
        </div>
        <div>
          <button @click="append(7)" value="7" class="numbers">7</button>
          <button @click="append(8)" value="8" class="numbers">8</button>
          <button @click="append(9)" value="9" class="numbers">9</button>
          <button @click="times()" class="operation">&#xd7;</button>
        </div>
        <div>
          <button @click="append(4)" value="4" class="numbers">4</button>
          <button @click="append(5)" value="5" class="numbers">5</button>
          <button @click="append(6)" value="6" class="numbers">6</button>
          <button @click="minus()" class="operation">-</button>
        </div>
        <div>
          <button @click="append(1)" value="1" class="numbers">1</button>
          <button @click="append(2)" value="2" class="numbers">2</button>
          <button @click="append(3)" value="3" class="numbers">3</button>
          <button @click="plus()" class="operation">+</button>
        </div>
        <div>
          <button @click="change()" id="sign">&#xb1;</button>
          <button @click="append(0)" value="0" class="numbers">0</button>
          <button @click="decimal()" id="sign" >.</button>
          <button @click="equal()" class="operation">=</button>
        </div>
      </div>
    </div>
    <div class="massage">{{massage}}</div>
  </template>
  
  <script>
import axios from 'axios'
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Calculator',

  data () {
    return {
      current: '',
      previous: '',
      temp: '',
      operator: '',
      pressed: '',
      massage: ''
    }
  },
  methods: {
    percent () {
      this.sendone('percent')
    },
    
    reverse () {
      this.sendone('reverse')
    },
    square () {
      this.sendone('square')
    },
    root () {
      this.sendone('root')
    },
    change () {
      this.sendone('sign')
    },
    divide () {
      if (this.operator !== '') {
        axios.get('http://localhost:8087/show')
          .then(response => { this.massage = response.data })
      } else {
        this.checkoperator('/')
        axios.get('http://localhost:8087/hide')
          .then(response => { this.massage = response.data })
      }
    },
    times () {
      if (this.operator !== '') {
        axios.get('http://localhost:8087/show')
          .then(response => { this.massage = response.data })
      } else {
        this.checkoperator('*')
        axios.get('http://localhost:8087/hide')
          .then(response => { this.massage = response.data })
      }
    },
    plus () {
      if (this.operator !== '') {
        axios.get('http://localhost:8087/show')
          .then(response => { this.massage = response.data })
      } else {
        this.checkoperator('+')
        axios.get('http://localhost:8087/hide')
          .then(response => { this.massage = response.data })
      }
    },
    minus () {
      if (this.operator !== '') {
        axios.get('http://localhost:8087/show')
          .then(response => { this.massage = response.data })
      } else {
        this.checkoperator('-')
        axios.get('http://localhost:8087/hide')
          .then(response => { this.massage = response.data })
      }
    },
    equal () {
      if (this.operator === '/') {
        this.send('divide')
      } else if (this.operator === '*') {
        this.send('times')
      } else if (this.operator === '+') {
        this.send('plus')
      } else if (this.operator === '-') {
        this.send('minus')
      }
      this.operator = ''
      this.pressed = 'true'
    },
    checkoperator (operation) {
      this.previous = this.temp
      this.temp = ''
      this.operator = operation
      this.pressed = ''
    },
    send (operator) {
      axios.get('http://localhost:8087/' + operator, { params: { previous: Number(this.previous), current: Number(this.temp) } })
        .then(response => { this.previous = this.temp; this.temp = response.data })
      this.current = this.temp
    },
    sendone (operator) {
      axios.get('http://localhost:8087/' + operator, { params: { current: Number(this.temp) } })
        .then(response => { this.previous = this.temp; this.temp = response.data })
      this.current = this.temp
    },
    decimal () {
      if (Number.isInteger(Number(this.temp))&& this.temp.substring( this.temp.length - 1)!='.') {
        this.temp = this.temp + '.'
      }
    },
    Delete () {
      this.current = ''
      this.temp = this.current
    },
    DeleteAll () {
      this.previous = ''
      this.current = ''
      this.temp = this.current
      this.operator = ''
    },
    remove () {
      this.temp = this.temp.substring(0, this.temp.length - 1)
    },
    append (number) {
      if (this.pressed === 'true') {
        this.current = this.temp
        this.temp = ''
        this.pressed = ''
      }
      this.temp = this.temp + number
      this.current = this.temp
    }
  }
}
</script>
  <style scoped>
  button {
    font-size: 17px;
    padding: 16px 16px;
    background-color: #f8f8f8;
    position: center;
    width: 141px;
    height: 72px;
    margin: 0 0;
    cursor: pointer;
    text-align: center;
  }
  .calculator{
    text-align: center;
    border: 1px solid #ccc;
    width: 567px;
    text-align: center;
    position: absolute;
    right: 398px;
    background-color: #27394b;
  }
  
  .operation{
    background-color:#d85fe4;
    color: white;
  }
  #text{
    background-color: #27394b;
    height: 70px;
    text-align: right;
    color: white;
    font-size: 30px;
  }
  .numbers{
    color:#d85fe4;
    background-color: #27394b;
  }
  #sign{
    background-color: #27394b;
    color:#d85fe4;
  }
  .massage{
    position: absolute;
    bottom: 98px;
    left: 680px;
    font-size: 30px;
  }
  #history{
    color: #727B86;
  }
  </style>
  