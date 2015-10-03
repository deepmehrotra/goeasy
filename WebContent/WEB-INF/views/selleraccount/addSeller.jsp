<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
    <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Seller Brands</h5>
                        </div>
                        <div class="ibox-content add-company">
                            <form role="form" class="form-horizontal">
                                <div class="col-sm-6">
                                    <div class="form-group"><label class="col-sm-4 control-label">Seller Name</label>

                                    <div class="col-sm-8"><input type="text" class="form-control"></div>
                                    </div>
                                    <div class="form-group"><label class="col-sm-4 control-label">Seller Alias</label>

                                    <div class="col-sm-8"><input type="text" class="form-control"></div>
                                    </div>
                                    <div class="form-group"><label class="col-sm-4 control-label required">Phone No. *</label>

                                    <div class="col-sm-8"><input type="text" class="form-control"></div>
                                    </div>
                                <div class="hr-line-dashed"></div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group"><label class="col-sm-4 control-label">TIN No</label>

                                    <div class="col-sm-8"><input type="text" class="form-control"></div>
                                    </div>
                                    <div class="form-group"><label class="col-sm-4 control-label">TAN No</label>

                                    <div class="col-sm-8"><input type="text" class="form-control"></div>
                                    </div>
                                    <div class="form-group"><label class="col-sm-4 control-label required">Email *</label>

                                    <div class="col-sm-8"><input type="text" class="form-control"></div>
                                    </div>
                                <div class="hr-line-dashed"></div>
                                </div>
                            </form>
                            <form role="form" class="form-horizontal">
                            <p>Add Seller Brands</p>
                                <div class="col-md-5"><input type="text" placeholder="Brand 1" class="form-control"></div>
                                <div class="col-md-5">
                                    <label title="Upload image file" for="inputImage" class="btn btn-white btn-block">
                                    <i class="fa fa-upload"></i>
                                        <input type="file" accept="image/*" name="file" id="inputImage" class="hide">
                                        Upload Logo
                                    </label>
                                </div>
                                <div class="col-md-2"><button class="btn btn-success" type="submit">Add Brand</button></div>
                            </form>
                            <div class="col-sm-12">
                                <div class="hr-line-dashed"></div>
                                    <button class="btn btn-primary pull-right" type="submit">Save</button>
                                </div>
                        </div>
                    </div>
                </div>
            </div>

</body>
</html>