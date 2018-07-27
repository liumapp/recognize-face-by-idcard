/**
 * @file pic-upload.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/24/18
 */
<template>
  <div>
    <br>
    <Row>
      <Col span="8" offset="8">
      <Upload
        multiple
        type="drag"
        action="#"
        accept=".jpg, .jpeg, .png"
        :before-upload="handleFileToBase64"
        :default-file-list="readyUploadPicList"
        :on-remove="handleRmFile">
        <div style="padding: 20px 0">
          <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
          <p>Click or drag pic files here to upload</p>
        </div>
      </Upload>
      </Col>
    </Row>
    <Row>
      <Col span="2" offset="14">
      <Button type="primary" @click="submitPic">提交</Button>
      </Col>
    </Row>
  </div>
</template>
<script>
import util from '@/libs/util'
export default {
  name: 'pic-upload',
  data () {
    return {
      picList: [],
      readyUploadPicList: []
    }
  },
  methods: {
    handleFileToBase64 (file) {
      let reader = new FileReader();
      let _vue = this;
      reader.readAsDataURL(file);
      reader.onload = function () {
        _vue.picList.push({name: file.name, content: reader.result});
        _vue.readyUploadPicList.push({name: file.name});
      }
      return false;
    },
    handleRmFile (file) {
      let _vue = this;
      let i = 0;
      this.picList.forEach(function (e) {
        if (e.name == file.name) {
          _vue.picList.splice(i,1);
          _vue.readyUploadPicList.splice(i,1);
        }
        i++;
      });
    },
    submitPic () {
      if (this.isTwoPic()) {
        util.post('upload/multybase64', this.picList).then(res => {
          this.$Message.success('pic upload success!');
          console.log(res.data);
//          this.$emit('next');
        });
      } else {
        this.$Message.error('必须上传两张用于匹对的照片');
      }
    },
    isTwoPic () {
      return this.picList.length == 2;
    }

  }
}
</script>
